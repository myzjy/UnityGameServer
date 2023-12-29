import { Package, Protocol } from 'pinus-protocol';
import { getLogger } from 'pinus-logger';
import * as path from 'path';
import { ISocket } from "pinus";

let logger = getLogger('pinus', path.basename(__filename));


let handlers: { [packageType: number]: (socket: ISocket, pkg: any) => void } = {};

let ST_INITED = 0;
let ST_WAIT_ACK = 1;
let ST_WORKING = 2;
let ST_CLOSED = 3;

let handleHandshake = function(socket: ISocket, pkg: any) {
  if (socket.state !== ST_INITED) {
    return;
  }
  try {
    socket.emit('handshake', JSON.parse(Protocol.strdecode(pkg.body)));
  } catch (ex) {
    socket.emit('handshake', {});
  }
};

let handleHandshakeAck = function(socket: ISocket, pkg: any) {
  if (socket.state !== ST_WAIT_ACK) {
    return;
  }
  socket.state = ST_WORKING;
  socket.emit('heartbeat');
};

let handleHeartbeat = function(socket: ISocket, pkg: any) {
  if (socket.state !== ST_WORKING) {
    return;
  }
  socket.emit('heartbeat');
};

let handleData = function(socket: ISocket, pkg: any) {
  // if (socket.state !== ST_WORKING) {
  //     return;
  // }
  console.log(pkg.toString());

  let data = JSON.parse(pkg)
  console.log(data.toString());
  socket.emit('message', data);
};

handlers[Package.TYPE_HANDSHAKE] = handleHandshake;
handlers[Package.TYPE_HANDSHAKE_ACK] = handleHandshakeAck;
handlers[Package.TYPE_HEARTBEAT] = handleHeartbeat;
handlers[Package.TYPE_DATA] = handleData;

export default function(socket: ISocket, pkg: any) {
  console.log("[pkg.type--->", pkg);
  let handler = handlers[Package.TYPE_DATA];
  if (!!handler) {
    handler(socket, pkg);
  } else {
    logger.error('找不到处理无效数据包.');
    socket.disconnect();
  }
}
