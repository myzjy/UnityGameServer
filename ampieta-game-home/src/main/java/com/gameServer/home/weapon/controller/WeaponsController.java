package com.gameServer.home.weapon.controller;

import com.gameServer.common.cache.weapon.CreateWeaponDefaultAnswer;
import com.gameServer.common.cache.weapon.CreateWeaponDefaultAsk;
import com.gameServer.common.entity.weapon.WeaponUsePlayerDataEntity;
import com.gameServer.common.ormEntity.WeaponGrowthValueDataConfigOrmEntity;
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
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.apache.commons.lang.NullArgumentException;
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
                var data = weaponService.getWeaponPlayerUserDataStruct(item, findWeaponConfig);
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
            logger.info("[_ WeaponUsePlayerDataEntity]:{}", JsonUtils.object2String(item));
            var data = weaponService.getWeaponPlayerUserDataStruct(item, findWeaponConfig);
            createList.add(data);
        }
        var response = WeaponPlayerUserDataResponse.ValueOf();
        response.setWeaponPlayerUserDataStructList(createList);
        NetContext.getRouter().send(session, response, gatewayAttachment);
    }

    @PacketReceiver
    public void atCreateWeaponDefaultAsk(Session session, CreateWeaponDefaultAsk answer) {
        logger.info("[当前服务器调用时间{}] [调用协议：atCreateWeaponDefaultAsk],UID:{}", TimeUtils.timeToString(TimeUtils.now()),answer.getUid());
        var config = OrmContext.getAccessor().load(answer.getPlayerId(), WeaponsDataConfigEntity.class);
        if (config == null) {
            // rpc 返回
            NetContext.getRouter().send(session, new CreateWeaponDefaultAnswer());
            throw new NullArgumentException(StringUtils.format("当前 查找武器id:{}，数据库 中不存在", answer.getPlayerId()));
            //return;
        }
        //数据库 中 武器数据 list
        var weaponUserList = OrmContext.getQuery(WeaponUsePlayerDataEntity.class).queryAll();
        //武器等级
        var weaponLv = OrmContext.getQuery(WeaponGrowthValueDataConfigOrmEntity.class).eq("quality", config.getWeaponQuality()).eq("lv", 1).queryFirst();
        var entity = WeaponUsePlayerDataEntity.ValueOf();
        var update = TimeUtils.timeToString(TimeUtils.now());
        entity.setId((long) weaponUserList.size() + 1);
        entity.setCreateAt(update);
        entity.setUpdateAt(update);
        entity.setWeaponId(config.getId());
        entity.setNowLvExp(0);
        entity.setNowLvMaxExp(weaponLv.getExp());
        entity.setLock(false);
        entity.setNowReinforcementEqualOrder(1);
        entity.setMaxReinforcementEqualOrder(config.getWeaponReinforcementEqualOrder());
        entity.setUserPlayerId(answer.getUserPlayerId());
        entity.setLv(1);
        var breakthrough = config.getWeaponBreakthrough();
        var breakthroughs = breakthrough.get(0);
        entity.setNowMaxLv(breakthroughs.getWeaponLv());
        entity.setUserUid(answer.getUid());
        entity.setWeaponName(config.getWeaponName());
        entity.setWeaponsSkill(config.getWeaponSkills());
        entity.setNowOrderNum(1);
        entity.setMaxOrderNum(config.getWeaponOrderNum());
        entity.setWeaponValue(config.getWeaponMainInitType());
        entity.setNewPc(true);
        entity.setNewAndroid(true);
        entity.setWeaponNum(1);
        OrmContext.getAccessor().insert(entity);
        var answerNew = new CreateWeaponDefaultAnswer();
        answerNew.setWeaponIndex(entity.getWeaponId());
        logger.info("WeaponUsePlayerDataEntity:{}", JsonUtils.object2String(entity));
        NetContext.getRouter().send(session, answerNew);
    }
}
