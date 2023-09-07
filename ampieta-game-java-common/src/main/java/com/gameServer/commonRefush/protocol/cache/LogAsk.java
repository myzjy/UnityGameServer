package com.gameServer.commonRefush.protocol.cache;

import com.zfoo.net.packet.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/8 22 50
 */
public class LogAsk implements IPacket {
    public static final transient short PROTOCOL_ID = 1021;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
