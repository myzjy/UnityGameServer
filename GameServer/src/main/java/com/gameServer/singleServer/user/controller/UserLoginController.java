package com.gameServer.singleServer.user.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.scheduler.util.TimeUtils;
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


    /**
     * 用户数据
     */
    @EntityCachesInjection
    private IEntityCaches<Long, PlayerUserEntity> UserModelDict;
    @EntityCachesInjection
    private IEntityCaches<Long, PhysicalPowerEntity> physicalPowerEntityIEntityCaches;

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
            NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(userEntity));
        }
        //相差的时间 精确到毫秒级别
        var differenceLastTime = TimeUtils.now() - data.getResidueNowTime();
        //剩余毫秒级别
        var differenceTime = differenceLastTime % 1000;
        //相差秒数
        var differenceToTime = differenceLastTime / 1000;
        if(differenceToTime>0){
            //代表 离线了1s以上
            //我离线时间是否超过我1点体力恢复时间
            if(data.getResidueTime()>differenceToTime){
                //没有超过
            }
            else{
                //超过了
            }
        }
//        /* *
//         * 开始计算 会恢复多少体力
//         *  根据时间 这里先用时间戳 去计算间隔多长时间了
//         * */
//        long lastTime = data.getMaxResidueEndTime();
//        //当前时间
//        var nowTime = TimeUtils.now();
//        /* *
//         * 相差的时间
//         * */
//        var differenceTime = nowTime - lastTime;
//        //这里剩余 毫秒
//        var differenceMiTime = differenceTime % 1000;
//        //相差时间 从毫秒换算成秒
//        var ç = differenceTime / 1000;
//        //恢复时间
//        var differenceNum = differenceToSeconds % 300;
//        //1点体力恢复 时间为 1点 5分钟 300秒 此时 多少点
//        int surplusNum = (int) (differenceToSeconds / 300);
//        data.setNowPhysicalPowerNum(data.getNowPhysicalPowerNum() + surplusNum);
//        if (data.getNowPhysicalPowerNum() >= data.getMaximumStrength()) {
//            //
////            data.setNowPhysicalPowerNum(data.);
//        }


        logger.info("[uid:{}]体力回复，[当前体力：{}] [目前等级为止的最大体力：{}]", numAsk.getUserId(), nowPhysicalPower, data.getMaximumStrength());
        NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(userEntity));
    }
}
