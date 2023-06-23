// 自己定义的connector 连接器

import {HybridSwitcher as Switcher, HybridSwitcherOptions} from "./MyHybridSwitcher"
import {EventEmitter} from "events";
import {IConnector} from "pinus/lib/interfaces/IConnector";
import {ProtobufComponent} from "pinus/lib/components/protobuf";
import {IComponent} from "pinus/lib/interfaces/IComponent";
import * as tls from "tls";
import * as net from "net";
import {pinus} from "pinus";
import {IMyHybridSocket} from "./IMyHybridSocket";
import {DictionaryComponent} from "pinus";
import {HandshakeCommand} from "./handshake";
import {HeartbeatCommand} from "./heartbeat";
import {MyHybridSocket} from "./MyHybridSocket";
import * as coder from "./coder";
import {handle} from "./kick";


let curId = 1;

export interface MyHybridConnectorOptions extends HybridSwitcherOptions {
    useDict?: boolean;
    useProtobuf?: boolean;
    distinctHost?: boolean;
    realIPKey?: string;   // 代理过后真实客户端ip获取字段
    realPortKey?: string; // 代理过后真实客户端port获取字段
}


export class MyHybridConnector extends EventEmitter implements IConnector {
    opts: MyHybridConnectorOptions;
    port: number;
    host: string;
    useDict: boolean;
    useProtobuf: boolean;
    handshake: HandshakeCommand;
    heartbeat: HeartbeatCommand;
    switcher: Switcher;
    connector: IConnector;
    dictionary: DictionaryComponent;
    protobuf: ProtobufComponent;
    decodeIO_protobuf: IComponent;
    distinctHost: boolean;
    ssl: tls.TlsOptions;
    listeningServer: net.Server | tls.Server;

    constructor(port: number, host: string, opts?: MyHybridConnectorOptions) {
        super();

        this.opts = opts || {};
        if (this.opts.realPortKey) {
            this.opts.realPortKey = opts.realPortKey.toLowerCase();
        }
        if (this.opts.realIPKey) {
            this.opts.realIPKey = opts.realIPKey.toLowerCase();
        }
        this.port = port;
        this.host = host;
        this.useDict = opts.useDict;
        this.useProtobuf = opts.useProtobuf;
        this.handshake = new HandshakeCommand(opts);
        this.heartbeat = new HeartbeatCommand(opts);
        this.distinctHost = opts.distinctHost;
        this.ssl = opts.ssl;

        this.switcher = null;
    }

    start(cb: () => void): void {
        let app = pinus.app;
        let self = this;
        let gensocket = function (socket: IMyHybridSocket, request: any) {
            let myHybridSocket = new MyHybridSocket(curId++, socket, request, self.opts);
            myHybridSocket.on('handshake', self.handshake.handle.bind(self.handshake, myHybridSocket));
            myHybridSocket.on('heartbeat', self.heartbeat.handle.bind(self.heartbeat, myHybridSocket));
            myHybridSocket.on('disconnect', self.heartbeat.clear.bind(self.heartbeat, myHybridSocket.id));
            myHybridSocket.on('closing', handle.bind(null, myHybridSocket));
            self.emit('connection', myHybridSocket);
        }
        this.connector = app.components.__connector__.connector;
        this.dictionary = app.components.__dictionary__;
        this.protobuf = app.components.__protobuf__;
        this.decodeIO_protobuf = app.components.__decodeIO__protobuf__;

        if (!this.ssl) {
            this.listeningServer = net.createServer();
        } else {
            this.listeningServer = tls.createServer(this.ssl);
            if (this.opts.sslWatcher) {
                this.opts.sslWatcher((opts) => {
                    (this.listeningServer as tls.Server).setSecureContext(opts);
                });
            }
        }

        this.switcher = new Switcher(this.listeningServer, self.opts);

        this.switcher.on('connection', function (socket, request) {
            gensocket(socket, request);
        });

        if (!!this.distinctHost) {
            this.listeningServer.listen(this.port, this.host);
        } else {
            this.listeningServer.listen(this.port);
        }

        process.nextTick(cb);
    }

    stop(force: boolean, cb: () => void) {
        this.switcher.close();
        this.listeningServer.close();

        process.nextTick(cb);
    }

    decode = coder.decode;

    encode = coder.encode;

}