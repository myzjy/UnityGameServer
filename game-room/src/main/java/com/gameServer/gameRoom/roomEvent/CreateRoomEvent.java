package com.gameServer.gameRoom.roomEvent;

import com.zfoo.event.model.event.IEvent;
import com.zfoo.net.session.Session;

/**
 * 创建房间 服务器内部通信时间
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/21 0:12
 */
public class CreateRoomEvent implements IEvent {


    /**
     * 发送session
     */
    private Session OtherSession;

    public static CreateRoomEvent ValueOf(Session session) {
        CreateRoomEvent value = new CreateRoomEvent();
        value.OtherSession = session;
        return value;
    }

    public Session getOtherSession() {
        return OtherSession;
    }

    public void setOtherSession(Session otherSession) {
        OtherSession = otherSession;
    }
}
