package com.gameServer.commonRefush.protocol.login;

import com.zfoo.net.packet.IPacket;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/18 23:44
 */
public class LogoutResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1003;
    private long uid;

    private long sid;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }
}
