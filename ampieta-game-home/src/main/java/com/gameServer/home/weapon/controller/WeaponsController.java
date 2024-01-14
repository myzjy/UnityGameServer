package com.gameServer.home.weapon.controller;

import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataRequest;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataStruct;
import com.gameServer.home.weapon.service.IWeaponService;
import com.gameServer.home.weapon.service.WeaponService;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/14 15 39
 */
@Component
public class WeaponsController {
    private static final Logger logger = LoggerFactory.getLogger(WeaponsController.class);
    @Autowired
    private IWeaponService weaponService;

    @PacketReceiver
    public void atWeaponPlayerUserDataRequest(Session session, WeaponPlayerUserDataRequest request, GatewayAttachment gatewayAttachment) {
        if (request.getFindUserId() < 1 && request.getFindWeaponId() < 1) {
        } else {
        }
    }

    public void SendFindUserWeaponList(Session session, WeaponPlayerUserDataRequest request, GatewayAttachment gatewayAttachment) {
        long uid = session.getUid();
        //查找到的 entity
        var list = OrmContext.getQuery(WeaponUsePlayerDataEntity.class).eq("userUid", uid).queryAll();
        //循环遍历
        for (var item : list) {
            var findWeaponConfig = OrmContext.getAccessor().load(item.getWeaponId(), WeaponsDataConfigEntity.class);
            if (findWeaponConfig == null) {
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，请联系客服"));
                return;
            }
            logger.info("[WeaponUsePlayerDataEntity]:{}", JsonUtils.object2String(item));
            var data = WeaponPlayerUserDataStruct.ValueOf();
            data.setId(item.getId());
            data.setWeaponIcons(findWeaponConfig.getWeaponBreakthrough());
        }
    }
}
