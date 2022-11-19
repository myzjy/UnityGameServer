package com.gameServer.singleServer.login.model;

import com.zfoo.event.model.event.IEvent;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/11/5 18:02
 */
public class LoginEvent implements IEvent {

    public static LoginEvent valueOf() {
        return new LoginEvent();
    }


}
