package com.gameServer.commonRefush.protocol.serverConfig;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since
 */
@Protocol(id = 1009)
public class ServerConfigRequest implements IPacket {
    public static final transient short PROTOCOL_ID = 1009;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
