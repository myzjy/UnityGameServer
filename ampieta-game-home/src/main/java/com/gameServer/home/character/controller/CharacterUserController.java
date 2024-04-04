package com.gameServer.home.character.controller;

import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.protocol.character.AcquireCharacterRequest;
import com.gameServer.common.protocol.character.CreateCharacterRequest;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
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
    public void atAcquireCharacterRequest(Session session, AcquireCharacterRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
    }

    @PacketReceiver
    public void atCreateCharacterRequest(Session session, CreateCharacterRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        var findId = new CharacterUserCompositeDataID();
        findId.setCharacterId(request.getCharacterId());
        findId.setUid(session.getUid());
        logger.info("[当前查找data:{}]", JsonUtils.object2String(findId));
        //查找到的角色相关数据
        var entity = OrmContext.getAccessor().load(findId, CharacterPlayerUserEntity.class);
        if (entity != null) {
            logger.info("[当前创建相关角色 data:{}] 数据库存在，请检查 协议消息", JsonUtils.object2String(findId));
            NetContext.getRouter().send(session, Error.valueOf("角色不可重复"));
            return;
        }
        entity = CharacterPlayerUserEntity.ValueOf();
        entity.setDataID(findId);
    }
}
