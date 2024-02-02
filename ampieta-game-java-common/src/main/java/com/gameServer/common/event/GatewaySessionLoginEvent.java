package com.gameServer.common.event;

import com.zfoo.event.model.IEvent;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/2/2 13 33
 */
public class GatewaySessionLoginEvent implements IEvent {

    private long sid;
    private long uid;

    public static GatewaySessionLoginEvent valueOf(long sid, long uid) {
        var event = new GatewaySessionLoginEvent();
        event.sid = sid;
        event.uid = uid;
        return event;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
