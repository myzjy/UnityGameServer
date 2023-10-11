package com.gameServer.home.equipment.controller;


import com.gameServer.common.ormEntity.EquipmentUserDataOrmEntity;
import com.gameServer.common.protocol.equipment.EquipmentAllDataRequest;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/25 11 07
 */
@Component
public class EquipmentController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    public EquipmentController() {
        logger.info("[EquipmentController]");
    }
    @PacketReceiver
    public void atEquipmentAllDataRequest(Session session, EquipmentAllDataRequest request, GatewayAttachment gateway){
        logger.info("获取所有圣遗物，请求者UID：{}",session.getUid());
        OrmContext.getQuery(EquipmentUserDataOrmEntity.class).eq("Uid",session.getUid()).queryAll();

    }
}
