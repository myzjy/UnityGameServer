package com.gameServer.singleServer.user.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/3/29 00 46
 */
@Component
public class UserLoginController {
    //log文件
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);
    @ResInjection
    private Storage<Integer, ConfigResource> configResourceStorage;

    /**
     * 用户数据
     */
    @EntityCachesInjection
    private IEntityCaches<Long, PlayerUserEntity> UserModelDict;
    @EntityCachesInjection
    private IEntityCaches<Long, PhysicalPowerEntity> physicalPowerEntityIEntityCaches;

    /**
     * 体力 rpc 登陆
     */
    @PacketReceiver
    public void atRefreshLoginPhysicalPowerNumAsk(Session session, RefreshLoginPhysicalPowerNumAsk numAsk) {
        var userId = numAsk.getUserId();
        var userEntity = UserModelDict.load(userId);
        if (userEntity.getId() == 0L) {
            NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(Error.valueOf(numAsk, I18nEnum.error_account_not_exit.toString())));
            return;
        }
        var data = physicalPowerEntityIEntityCaches.load(numAsk.getUserId());
        if (data == null) {
            logger.error("[uid:{}]体力缓存数据库不存在，请创建，流程有问题", numAsk.getUserId());
            NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(Error.valueOf(numAsk, I18nEnum.error_login_process_not.toString())));
            return;
        }
        //第一次创建账号 体力恢复满
        if (userEntity.getLastLoginTime() < 1) {
            //这里就不用 error 提示了
            logger.warn("[uid:{}],当前玩家为第一次创建，体力不需要恢复直接满格", userId);
//            data.setNowPhysicalPowerNum();
        }

        //当前体力
        var nowPhysicalPower = data.getNowPhysicalPowerNum();
        if (nowPhysicalPower >= data.getMaximumStrength()) {
            logger.info("[uid:{}]体力已满[当前体力：{}] [目前等级为止的最大体力：{}]", numAsk.getUserId(), nowPhysicalPower, data.getMaximumStrength());
            //体力满了
            NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf());
        }
        //相差的时间 精确到毫秒级别
        var differenceLastTime = TimeUtils.now() - data.getResidueNowTime();
        //剩余毫秒级别
        var differenceTime = differenceLastTime % 1000;
        //相差秒数
        var differenceToTime = differenceLastTime / 1000;
        if (differenceToTime > 0) {
            //代表 离线了1s以上
            //我离线时间是否超过我1点体力恢复时间
            if (data.getResidueTime() > differenceToTime) {
                //没有超过
                data.setResidueTime(data.getResidueTime() - (int) differenceTime);
            } else {
                //因为这里的时间需要减
                var setMaximusResidue = (data.getMaximusResidueEndTime() - (int) differenceTime);
                setMaximusResidue = Math.max(setMaximusResidue, 0);
                data.setMaximusResidueEndTime(setMaximusResidue);
                //超过了
                var differenceNum = (int) differenceTime - data.getResidueTime();
                //离线时间超过当前 1点体力恢复时间，并切剩余时间大于1点体力恢复时间
                if (differenceNum > 300) {
                    //可以恢复多少点离线体力
                    var num = differenceNum / 300;

                    data.setNowPhysicalPowerNum(data.getNowPhysicalPowerNum() + num);
                    data.setResidueNowTime(TimeUtils.now());
                    //增加的体力是否大于 当前最大体力了
                    if (data.getNowPhysicalPowerNum() >= data.getMaximumStrength()) {
                        data.setNowPhysicalPowerNum(data.getMaximumStrength());
                        //恢复时间统一归零
                        data.setResidueTime(0);
                        data.setMaximusResidueEndTime(0);
                        data.setMaxResidueEndTime(0);
                    }

                } else {
                    //加一点体力
                    data.setNowPhysicalPowerNum(data.getNowPhysicalPowerNum() + 1);
                    data.setResidueTime(differenceNum);
                    data.setResidueNowTime(TimeUtils.now());
                }
            }
        }
        userEntity.setNowPhysicalPowerNum(data.getNowPhysicalPowerNum());
        UserModelDict.update(userEntity);
        OrmContext.getAccessor().update(userEntity);
        //更新数据库
        physicalPowerEntityIEntityCaches.update(data);
        OrmContext.getAccessor().update(data);

        logger.info("[uid:{}]体力回复，[当前体力：{}] [目前等级为止的最大体力：{}]", numAsk.getUserId(), nowPhysicalPower, data.getMaximumStrength());
        NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf());
    }

    @PacketReceiver
    public void atCreatePhysicalPowerAsk(Session session, CreatePhysicalPowerAsk ask) {
        logger.info("处理");
        var physicalData = physicalPowerEntityIEntityCaches.load(ask.getUid());
        logger.info("是否有{}", physicalData);
        var userData = UserModelDict.load(ask.getUid());
        var config = configResourceStorage.get(userData.getPlayerLv());

        //数据库中没有 需要创建
        var createPhysical = PhysicalPowerEntity.ValueOf(ask.getUid(), 0, config.getMaxPhysical(), 0, config.getMaxPhysical(), 0);
        OrmContext.getAccessor().insert(createPhysical);
        logger.info("[UserLoginController] 体力数据创建成功 插入数据库成功");
        //设置最大体力
        userData.setNowLvMaxExp(config.getMaxExp());
        //缓存读取
        physicalData = physicalPowerEntityIEntityCaches.load(ask.getUid());
        userData.setNowPhysicalPowerNum(physicalData.getNowPhysicalPowerNum());
        UserModelDict.update(userData);
        NetContext.getRouter().send(session, new CreatePhysicalPowerAnswer());

    }
}
