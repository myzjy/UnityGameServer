package com.gameServer.home.user.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/3/29 00 46
 */
@Component
public class UserLoginController {
    //log文件
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private IPhysicalPowerService physicalPowerService;

    /**
     * 体力 rpc 登陆
     */
    @PacketReceiver
    public void atRefreshLoginPhysicalPowerNumAsk(Session session, RefreshLoginPhysicalPowerNumAsk numAsk) {
        var userId = numAsk.getUserId();
        var userEntity = userLoginService.LoadPlayerUserEntity(userId);
        var config = userLoginService.GetConfigResourceData(userEntity.getPlayerLv());
        if (userEntity.getId() == 0L) {
            NetContext.getRouter().send(session,
                    RefreshLoginPhysicalPowerNumAnswer.ValueOf(Error.valueOf(numAsk, I18nEnum.error_account_not_exit.toString())));
            return;
        }
        var data = userLoginService.GetToUserIDPhysicalPowerEntity(numAsk.getUserId());
        if (data == null) {
            logger.error("[uid:{}]体力缓存数据库不存在，请创建，流程有问题", numAsk.getUserId());
            NetContext.getRouter().send(session,
                    RefreshLoginPhysicalPowerNumAnswer.ValueOf(Error.valueOf(numAsk, I18nEnum.error_login_process_not.toString())));
            return;
        }
        //第一次创建账号 体力恢复满
        if (userEntity.getLastLoginTime() < 1) {
            //这里就不用 error 提示了
            logger.warn("[uid:{}],当前玩家为第一次创建，体力不需要恢复直接满格", userId);
        }

        //当前体力
        var nowPhysicalPower = data.getNowPhysicalPowerNum();
        if (nowPhysicalPower >= data.getMaximumStrength()) {
            logger.info("[uid:{}]体力已满[当前体力：{}] [目前等级为止的最大体力：{}]", numAsk.getUserId(), nowPhysicalPower, data.getMaximumStrength());
            //体力满了
            NetContext.getRouter().send(session,
                    RefreshLoginPhysicalPowerNumAnswer.ValueOf());
        }
        //相差的时间 精确到毫秒级别
        var differenceLastTime = TimeUtils.now() - data.getResidueNowTime();
        //剩余毫秒级别
        var differenceTime = differenceLastTime % 1000;
        //相差秒数
        var differenceToTime = (int) (differenceLastTime / 1000);
        var dateTime = TimeUtils.timeToString(data.getResidueNowTime());

        logger.info("[uid:{}] 体力恢复实时时间：{},更当前时间相差秒数为{}", userEntity.getId(), dateTime, differenceToTime);
        if (differenceToTime > 0) {
            /**
             * 体力完全恢复 剩余时间
             */
            data=physicalPowerService.PhysicalPowerGetResidueEndTime(data,differenceToTime,config,userEntity);

            data = physicalPowerService.PhysicalPowerGetResidueTime(data, differenceToTime, config, userEntity);
        }
        userEntity.setNowPhysicalPowerNum(data.getNowPhysicalPowerNum());
        userLoginService.UpdatePlayerUserEntity(userEntity);
        //更新 缓存 数据库
        userLoginService.UpDataPhysicalPowerEntityCaches(data);
        OrmContext.getAccessor().update(data);
        logger.info("[玩家：{}] 更新 PhysicalPowerEntity 数据库", data.getId());
        logger.info("[uid:{}]体力回复，[当前体力：{}] [目前等级为止的最大体力：{}]", numAsk.getUserId(), nowPhysicalPower, data.getMaximumStrength());
        NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf());
    }

    @PacketReceiver
    public void atCreatePhysicalPowerAsk(Session session, CreatePhysicalPowerAsk ask) {
        var physicalData = OrmContext.getAccessor().load(ask.getUid(),
                PhysicalPowerEntity.class);
        logger.info("是否有{}", physicalData);
        var userData = userLoginService.LoadPlayerUserEntity(ask.getUid());
        var config = userLoginService.GetConfigResourceData(userData.getPlayerLv());
        if (physicalData == null) {
            //数据库中没有 需要创建
            var createPhysical =
                    PhysicalPowerEntity.ValueOf(ask.getUid(),
                            0,
                            config.getMaxPhysical(),
                            0,
                            config.getMaxPhysical(),
                            0);
            OrmContext.getAccessor().insert(createPhysical);
        }

        logger.info("[UserLoginController] 体力数据创建成功 插入数据库成功");
        //设置最大经验
        userData.setNowLvMaxExp(config.getMaxExp());
        //缓存读取
        physicalData = userLoginService.GetToUserIDPhysicalPowerEntity(ask.getUid());
        userData.setNowPhysicalPowerNum(physicalData.getNowPhysicalPowerNum());
        userLoginService.UpdatePlayerUserEntity(userData);
        logger.info("[玩家{}]更新玩家数据库 ", userData.getId());
        NetContext.getRouter().send(session, new CreatePhysicalPowerAnswer());

    }

    @EventReceiver
    public void onCreateOrmAccesTimeEvent(CreateOrmAccesTimeEvent event) {
        if (userLoginService.IsAcesGameTimeResource()) {
            logger.error("未配置服务器开始时间");
            return;
        }
        var dict = userLoginService.GetAccesTimeAll();
        for (var item : dict) {
            var entity = OrmContext.getAccessor().load(item.getTimeID(), AccessGameTimeEntity.class);
            if (entity == null) {
                //数据库没有相关配置
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                var dateTime = TimeUtils.timeToString(item.getTime());
                logger.info("{}", dateTime);
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                OrmContext.getAccessor().insert(entity);
            } else {
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                var dateTime = TimeUtils.timeToString(item.getTime());
                logger.info("{}", dateTime);
                OrmContext.getAccessor().update(entity);
            }
        }
    }
}
