package com.gameServer.commonRefush.event.bag;

import com.zfoo.event.model.event.IEvent;
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

    public static StartLoginBagEvent ValueOf(Session _session) {
        var value = new StartLoginBagEvent();
        value.session = _session;
        return value;
    }

    public Session getSession() {
        return session;
    }
}
