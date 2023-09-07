package com.gameServer.commonRefush.event.bag;

import com.zfoo.event.model.IEvent;
import com.zfoo.net.session.Session;

/**
 * 登录的时候 获取背包数据
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/24 23:34
 */
public class StartLoginBagEvent implements IEvent {
    private Session session;
    /**
     * 获取的背包type 根据type 去获取对应背包数据
     */
    private int type;

    public static StartLoginBagEvent ValueOf(Session _session, int type) {
        var value = new StartLoginBagEvent();
        value.session = _session;
        value.type = type;
        return value;
    }

    public Session getSession() {
        return session;
    }

    public int getType() {
        return type;
    }
}
