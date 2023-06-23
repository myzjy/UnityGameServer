import {getLogger} from "pinus-logger";
import * as path from "path";
import {EventEmitter} from "events";
import {ISocket} from "pinus";
import * as WebSocket from 'ws';

import {IMyHybridSocket} from "./IMyHybridSocket";
import {TcpSocket} from "pinus/lib/connectors/hybrid/tcpsocket";
import {Package} from "pinus-protocol";
import handler from "./handler";

let logger = getLogger('pinus', path.basename(__filename));
let ST_INITED = 0;
let ST_WAIT_ACK = 1;
let ST_WORKING = 2;
let ST_CLOSED = 3;


export interface MyHybridSocketOptions {
    realIPKey?: string;   // 代理过后真实客户端ip获取字段 Header name must be lower-cased.
    realPortKey?: string; // 代理过后真实客户端port获取字段 Header name must be lower-cased.
}

/**
 * 套接字类，封装套接字和websocket，为上层提供统一的接口。
 */
export class MyHybridSocket extends EventEmitter implements ISocket {
    state: number;
    id: number;
    socket: IMyHybridSocket;
    remoteAddress: { ip: string, port: number };

    constructor(id: number, socket: IMyHybridSocket, request: any, opts: MyHybridSocketOptions) {
        super();
        this.id = id;
        this.socket = socket;
        if (request && (opts.realIPKey || opts.realPortKey)) {
            //读取头部的ip
            let ip = request['headers'][opts.realIPKey];
            if (ip) {
                this.remoteAddress = {
                    ip: ip,
                    port: opts.realPortKey ? request['headers'][opts.realPortKey] : 0
                }
            }
        }
        if (!this.remoteAddress) {
            //tcpSocket
            if (!(socket as TcpSocket)._socket) {
                this.remoteAddress = {
                    ip: (socket as any).address().address,
                    port: (socket as any).address().port
                };
            } else {
                this.remoteAddress = {
                    ip: (socket as TcpSocket)._socket.remoteAddress,
                    port: (socket as TcpSocket)._socket.remotePort
                };
            }
        }
        let self = this;

        socket.once('close', this.emit.bind(this, 'disconnect'));
        socket.on('error', this.emit.bind(this, 'error'));

        socket.on('message', function (msg) {
            if (msg) {
                // msg = Package.decode(msg);
                handler(self, msg);
            }
        });

        this.state = ST_INITED;

    }

    /**
     * Send raw byte data.
     *
     * @api private
     */
    sendRaw(msg: any) {
        // if (this.state !== ST_WORKING) {
        //     return;
        // }
        let self = this;
        this.socket.send(msg, {binary: true}, (err) => {
            if (!!err) {
                logger.error('Websocket发送二进制数据失败: %j', err.stack);
                return;
            }
        });
    }

    /**
     * 发送字节数据包到客户端。
     *
     * @param  {Buffer} msg byte data
     */
    send(msg: any) {
        console.log(JSON.stringify(msg));
        if (msg instanceof String) {
            msg = Buffer.from(msg as string);
        } else if (!(msg instanceof Buffer)) {
            msg = Buffer.from(JSON.stringify(msg));
        }
        this.sendRaw(msg);
    }

    /**
     * Send byte data packages to client in batch.
     *
     * @param  {Buffer} msgs byte data
     */
    sendBatch(msgs: any[]) {
        console.log("sendBatch:--->", msgs.toString());
        // let rs = [];
        // for (let i = 0; i < msgs.length; i++) {
        //     let src = Package.encode(Package.TYPE_DATA, msgs[i]);
        //     rs.push(src);
        // }
        this.sendRaw(Buffer.concat(msgs));
    }

    /**
     * Send message to client no matter whether handshake.
     *
     * @api private
     */
    sendForce(msg: any) {
        if (this.state === ST_CLOSED) {
            return;
        }
        this.socket.send(msg, {binary: true});
    }

    /**
     * Response handshake request
     *
     * @api private
     */
    handshakeResponse(resp: any) {
        if (this.state !== ST_INITED) {
            return;
        }

        this.socket.send(resp, {binary: true});
        this.state = ST_WAIT_ACK;
    }

    /**
     * Close the connection.
     *
     * @api private
     */
    disconnect() {
        if (this.state === ST_CLOSED) {
            return;
        }

        this.state = ST_CLOSED;
        this.socket.emit('close');
        this.socket.close();
    }

}