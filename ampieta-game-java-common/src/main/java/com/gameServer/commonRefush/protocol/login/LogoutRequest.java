package com.gameServer.commonRefush.protocol.login;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/18 23:46
 */
@Protocol(id = 1002)
public class LogoutRequest implements IPacket {
    public static final transient short PROTOCOL_ID = 1002;
    public static LogoutRequest valueOf() {
        return new LogoutRequest();
    }


    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
