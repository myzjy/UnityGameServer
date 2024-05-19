package com.gameServer.common.event;

import com.zfoo.event.model.IEvent;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;

/**
 * 角色 config 获取相关 事件
 *
 * @author zjy
 * @version 1.0
 * @since 2024/5/19 14 05
 */
public class CharacterConfigLoginEvent implements IEvent {
    /**
     * 连接 信息
     */
    private Session session;
    /**
     * 网关信息 转发给网关
     */
    private GatewayAttachment attachment;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public GatewayAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(GatewayAttachment attachment) {
        this.attachment = attachment;
    }

    public static CharacterConfigLoginEvent valueOf(Session session, GatewayAttachment attachment) {
        CharacterConfigLoginEvent event = new CharacterConfigLoginEvent();
        event.setSession(session);
        event.setAttachment(attachment);
        return event;
    }
}
