package com.gameServer.home.weapon.controller;

import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.WeaponsDataConfigEntity;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataRequest;
import com.gameServer.common.protocol.weapon.WeaponPlayerUserDataResponse;
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

import java.util.ArrayList;
import java.util.Map;

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
            SendFindUserWeaponList(session, request, gatewayAttachment);
        } else {
            //需要查找单独的装备了
            var uid = request.getFindUserId();
            var weaponId = request.getFindWeaponId();
            var findUidDataList = OrmContext.getQuery(WeaponUsePlayerDataEntity.class).eq("userUid", uid);
            //查找到的
            var findWeapon = findUidDataList.eq("weaponId", weaponId).queryAll();
            var create = getWeaponPlayerUserDataStructs();
            //循环遍历
            for (var item : findWeapon) {
                var findWeaponConfig = OrmContext.getAccessor().load(item.getWeaponId(), WeaponsDataConfigEntity.class);
                if (findWeaponConfig == null) {
                    NetContext.getRouter().send(session, Error.valueOf("数据库错误，请联系客服"));
                    return;
                }
                logger.info("查找玩家：{}", item.getUserUid());
                logger.info("[WeaponUsePlayerDataEntity]:{}", JsonUtils.object2String(item));
                var data = getWeaponPlayerUserDataStruct(item, findWeaponConfig);
                create.add(data);
            }
            var response = WeaponPlayerUserDataResponse.ValueOf();
            response.setWeaponPlayerUserDataStructList(create);
            response.setUsePlayerUid(uid);
            NetContext.getRouter().send(session, response, gatewayAttachment);
        }
    }

    private ArrayList<WeaponPlayerUserDataStruct> getWeaponPlayerUserDataStructs() {
        return new ArrayList<>();
    }

    public void SendFindUserWeaponList(Session session, WeaponPlayerUserDataRequest request, GatewayAttachment gatewayAttachment) {
        long uid = session.getUid();
        //查找到的 entity
        var list = OrmContext.getQuery(WeaponUsePlayerDataEntity.class).eq("userUid", uid).queryAll();
        var createList = new ArrayList<WeaponPlayerUserDataStruct>();
        //循环遍历
        for (var item : list) {
            var findWeaponConfig = OrmContext.getAccessor().load(item.getWeaponId(), WeaponsDataConfigEntity.class);
            if (findWeaponConfig == null) {
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，请联系客服"));
                return;
            }
            logger.info("[WeaponUsePlayerDataEntity]:{}", JsonUtils.object2String(item));
            var data = getWeaponPlayerUserDataStruct(item, findWeaponConfig);
            createList.add(data);
        }
        var response = WeaponPlayerUserDataResponse.ValueOf();
        response.setWeaponPlayerUserDataStructList(createList);
        NetContext.getRouter().send(session, response, gatewayAttachment);
    }

    private WeaponPlayerUserDataStruct getWeaponPlayerUserDataStruct(WeaponUsePlayerDataEntity item, WeaponsDataConfigEntity findWeaponConfig) {
        var data = WeaponPlayerUserDataStruct.ValueOf();
        data.setId(item.getId());
        data.setWeaponIcons(findWeaponConfig.getIconResource());
        data.setHaveTimeAt(item.getCreateAt());
        data.setWeaponName(item.getWeaponName());
        data.setWeaponType(findWeaponConfig.getWeaponType());
        data.setNowLv(item.getLv());
        data.setNowMaxLv(item.getNowMaxLv());
        data.setNowLvExp(item.getNowLvExp());
        data.setNowSkills(item.getWeaponsSkill());
        data.setWeaponMainValue(item.getWeaponValue());
        data.setWeaponMainValueType(findWeaponConfig.getWeaponMainInitType());
        data.setWeaponModelNameIcons("");
        data.setNowLvMaxExp(item.getNowLvMaxExp());
        return data;
    }
}
