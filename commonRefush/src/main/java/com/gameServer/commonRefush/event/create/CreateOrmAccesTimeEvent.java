package com.gameServer.commonRefush.event.create;

import com.zfoo.event.model.event.IEvent;

/**
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/11 23 02
 */
public class CreateOrmAccesTimeEvent implements IEvent {
    public static CreateOrmAccesTimeEvent ValueOf(){
        var event=new CreateOrmAccesTimeEvent();
        return event;
    }
}
