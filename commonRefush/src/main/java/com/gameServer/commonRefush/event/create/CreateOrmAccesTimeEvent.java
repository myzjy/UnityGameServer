package com.gameServer.commonRefush.event.create;

import com.zfoo.event.model.event.IEvent;

/**
 * 把表中的服务器开启时间，写入数据库中
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/11 23 02
 */
public class CreateOrmAccesTimeEvent implements IEvent {
    public static CreateOrmAccesTimeEvent ValueOf() {
        var event = new CreateOrmAccesTimeEvent();
        return event;
    }
}
