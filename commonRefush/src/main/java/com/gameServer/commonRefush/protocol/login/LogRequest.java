package com.gameServer.commonRefush.protocol.login;

import com.zfoo.protocol.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/8 22 45
 */
public class LogRequest implements IPacket {
    public static final transient short PROTOCOL_ID = 1019;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
