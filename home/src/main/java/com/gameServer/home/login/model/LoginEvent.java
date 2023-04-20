package com.gameServer.home.login.model;

import com.zfoo.event.model.event.IEvent;

/**
 * 登录事件 目前可能用不到
 * @author zjy
 * @version 1.0
 * @since 2022/11/5 18:02
 */
public class LoginEvent implements IEvent {

    public static LoginEvent valueOf() {
        return new LoginEvent();
    }


}
