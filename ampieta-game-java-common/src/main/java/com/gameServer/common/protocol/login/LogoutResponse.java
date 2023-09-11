package com.gameServer.common.protocol.login;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/18 23:44
 */
@Protocol(id = 1003)
public class LogoutResponse implements IPacket {
    private long uid;
    private long sid;

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
