package com.gameServer.home.user.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
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
//            data.setNowPhysicalPowerNum();
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
            //代表 离线了1s以上
            //我离线时间是否超过我1点体力恢复时间
            if (data.getResidueTime() >= 0) {
                var reResidueTime = data.getResidueTime() > 0 ?
                        data.getResidueTime() - differenceTime :
                        differenceTime;
                var reResidueEndTime = data.getResidueEndTime() - differenceToTime;
                logger.info("[uid:{}] 体力恢复实时时间：{},data.getResidueTime() - (int) differenceTime：{}", userEntity.getId(), dateTime, reResidueTime);
                logger.info("[uid:{}] 体力恢复实时时间：{},data.getResidueEndTime() - (int) differenceToTime：{}", userEntity.getId(), dateTime, reResidueEndTime);


            } else {
                // 数据库中存放 恢复时间为负 是错误，
                //这里就是修复 这个错误
                if (data.getNowPhysicalPowerNum() >= 0) {
                    /* *
                     * 当前体力是否大于 或者等于 最大体力
                     * 如果是就必须给予限制
                     */
                    if (data.getNowPhysicalPowerNum() >= data.getMaximumStrength()) {
                        logger.info("[uid:{}],当前体力：{}， 最大体力：{}", userEntity.getId(), data.getNowPhysicalPowerNum(), data.getMaximumStrength());
                        data.setNowPhysicalPowerNum(data.getMaximumStrength());
                        logger.info("[uid:{}],限制完的当前体力：{}， 最大体力：{}", userEntity.getId(), data.getNowPhysicalPowerNum(), data.getMaximumStrength());
                        /* *
                         * 所有时间全部为0
                         */
                        data.setResidueTime(0);
                        data.setMaximusResidueEndTime(0);
                        data.setResidueEndTime(0);
                        data.setResidueNowTime(0);
                        logger.info("[uid:{}],时间初始化完毕", userEntity.getId());

                    } else {
                        /* *
                         * 体力没满 但是进入这里代表 数据库中的恢复时间是错误的
                         */
                        var residue = (data.getMaximumStrength() - data.getNowPhysicalPowerNum()) * config.getResidueTime();
                        logger.info("[uid:{}],还有{} 体力才能恢复满,总恢复时间为{}", userEntity.getId(), (data.getMaximumStrength() - data.getNowPhysicalPowerNum()), residue);
                        data.setResidueNowTime(TimeUtils.now());
                        data.setResidueTime(config.getResidueTime());
                        data.setResidueEndTime(residue);
                        logger.info("[uid:{}],时间设置完毕", userEntity.getId());
                    }
                } else {
                    /* *
                     * 当前体力小于0 一般使用体力的时候 就给予限制，出问题一般要在扣除体力的 controller 寻找问题、
                     * 不需要在这里进行任何处理
                     */
                    logger.error("[uid:{}],数据库出现问题，请检查,当前玩家数据库中体力值为负", userEntity.getId());
                }
            }

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
        var physicalData = OrmContext.getAccessor().load(ask.getUid(), PhysicalPowerEntity.class);
        logger.info("是否有{}", physicalData);
        var userData = userLoginService.LoadPlayerUserEntity(ask.getUid());
        var config = userLoginService.GetConfigResourceData(userData.getPlayerLv());
        if (physicalData == null) {
            //数据库中没有 需要创建
            var createPhysical =
                    PhysicalPowerEntity.ValueOf(
                            ask.getUid(),
                            0,
                            config.getMaxPhysical(),
                            0, config.getMaxPhysical(), 0);
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
