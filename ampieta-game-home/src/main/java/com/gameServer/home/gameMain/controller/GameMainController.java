package com.gameServer.home.gameMain.controller;

import com.gameServer.common.protocol.gameMain.OpenGameMainUIPanelRequest;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/25 00 02
 */
@Component
public class GameMainController {
    private static final Logger logger = LoggerFactory.getLogger(GameMainController.class);

    @PacketReceiver
    private void atOpenGameMainUIPanelRequest(Session session, OpenGameMainUIPanelRequest request, GatewayAttachment attachment) {
        logger.info("调用协议号：1049");
        // 返回 角色 相关信息
        // 返回 资源相关信息
        //返回体力相关
    }
}
