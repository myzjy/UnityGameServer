package com.gameServer.home.character.controller;

import com.gameServer.common.protocol.character.AcquireCharacterRequest;
import com.gameServer.common.protocol.character.CreateCharacterRequest;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 角色 协议回调
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 23 43
 */
@Component
public class CharacterUserController {
    private static final Logger logger = LoggerFactory.getLogger(CharacterUserController.class);
    @PacketReceiver
    public void atAcquireCharacterRequest(Session session, AcquireCharacterRequest request, GatewayAttachment gatewayAttachment){
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());

    }
    @PacketReceiver
    public void atCreateCharacterRequest(Session session, CreateCharacterRequest request, GatewayAttachment gatewayAttachment){
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());

    }
}
