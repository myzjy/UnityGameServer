package com.gameServer.commonRefush.protocol.serverConfig;

import com.zfoo.protocol.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since
 */
public class ServerConfigRequest implements IPacket {
    public static final transient short PROTOCOL_ID = 1009;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
