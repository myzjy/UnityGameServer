package com.gameServer.home.PhysicalPower.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.gameServer.commonRefush.protocol.physicalPower.*;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.net.NetContext;
import com.zfoo.net.handler.codec.json.JsonPacket;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 体力控制相关
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/16 23 41
 */
@Component
public class PhysicalPowerUsePropsController {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalPowerUsePropsController.class);

    /**
     * 体力 service
     */
    @Autowired
    private IPhysicalPowerService iPhysicalPowerService;

    @Autowired
    private IUserLoginService userLoginService;
    /**
     * 使用体力的控制 客户端 回调
     *
     * @param session           用户
     * @param request           使用道具的request
     * @param gatewayAttachment 网关
     */
    @PacketReceiver
    public void atPhysicalPowerUserPropsRequest(Session session, PhysicalPowerUserPropsRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("[uid:{}] 调用使用体力 开始一张战斗之后就会扣除", session.getUid());
        var physicalData = iPhysicalPowerService.FindOnePhysicalPower(session.getUid());
        var userData = userLoginService.LoadPlayerUserEntity(session.getUid());
        var configData = userLoginService.GetConfigResourceData(userData.getPlayerLv());
        if (physicalData.getNowPhysicalPowerNum() >= physicalData.getMaximumStrength()) {
            logger.info("当前体力：{}", physicalData.getNowPhysicalPowerNum());
            physicalData.setResidueTime(0);
            physicalData.setMaximusResidueEndTime(0);
            physicalData.setMaxResidueEndTime(0);
            physicalData.setResidueEndTime(0);
            physicalData.setResidueNowTime(0);
            iPhysicalPowerService.UpdatePhysicalPowerEntityOrm(physicalData);
            OrmContext.getAccessor().update(physicalData);
            logger.info("[uid:{}]当前体力：{} 满的，清空体力时间数据 完成", physicalData.getId(), physicalData.getNowPhysicalPowerNum());
        }
        /**
         * physicalReduce= 当前体力-使用的体力值
         */
        var physicalReduce = physicalData.getNowPhysicalPowerNum() - request.getUsePropNum();
        /**
         * 减少体力是否大于 最大体力
         */
        if (physicalReduce >= physicalData.getMaximumStrength()) {
            logger.info("当前扣除体力值：{}，扣除完的体力，依旧满格体力，当前体力：{},扣除完体力值：{}", request.getUsePropNum(), physicalData.getNowPhysicalPowerNum(), physicalReduce);
            physicalData.setNowPhysicalPowerNum(physicalReduce);
            //满格体力
            physicalData.setResidueTime(0);
            physicalData.setMaximusResidueEndTime(0);
            physicalData.setMaxResidueEndTime(0);
            physicalData.setResidueEndTime(0);
            physicalData.setResidueNowTime(0);
            iPhysicalPowerService.UpdatePhysicalPowerEntityOrm(physicalData);
            logger.info("[玩家：{}] 更新 PhysicalPowerEntity 数据库", physicalData.getId());
            var response = PhysicalPowerUserPropsResponse.ValueOf(
                    physicalData.getNowPhysicalPowerNum(),
                    physicalData.getResidueTime(),
                    physicalData.getMaximumStrength(),
                    physicalData.getMaximusResidueEndTime(),
                    physicalData.getResidueNowTime());
            logger.info("[玩家：{}],体力满,数据：{}", physicalData.getId(), JsonUtils.object2StringTurbo(response));
            //当前体力当好使用完
            NetContext.getRouter().send(session, response, gatewayAttachment);
        } else {
            logger.info("当前扣除体力值：{}，扣除体力,当前体力：{},扣除完体力值：{}", request.getUsePropNum(), physicalData.getNowPhysicalPowerNum(), physicalReduce);
            //需要将体力相关修改
            if (physicalReduce >= 0) {
                //使用体力恢复时间
                var residueSum = request.getUsePropNum() * configData.getResidueTime();
                //转换成毫秒
                var residueSumLong = residueSum * 1000;
                var residueOneSumLong = configData.getResidueTime() * 1000;
                if (physicalData.getResidueTime() < 1) {
                    //设置1点体力 恢复时间
                    physicalData.setResidueTime(configData.getResidueTime());
                }

                if (physicalData.getMaxResidueEndTime() < 1) {
                    physicalData.setMaxResidueEndTime(TimeUtils.now() + residueSumLong);
                } else {
                    physicalData.setMaxResidueEndTime(physicalData.getMaximusResidueEndTime() + residueSumLong);
                }

                if (physicalData.getResidueEndTime() < 1) {
                    //
                    physicalData.setResidueEndTime(TimeUtils.now() + residueOneSumLong);
                }

                physicalData.setNowPhysicalPowerNum(physicalReduce);

                physicalData.setMaximusResidueEndTime(physicalData.getMaximusResidueEndTime() + residueSum);
                physicalData.setResidueNowTime(TimeUtils.now());
                //更新数据库内容
                iPhysicalPowerService.UpdatePhysicalPowerEntityOrm(physicalData);
                logger.info("[玩家：{}] 更新 PhysicalPowerEntity 数据库", physicalData.getId());
                var response = PhysicalPowerUserPropsResponse.ValueOf(
                        physicalData.getNowPhysicalPowerNum(),
                        physicalData.getResidueTime(),
                        physicalData.getMaximumStrength(),
                        physicalData.getMaximusResidueEndTime(),
                        physicalData.getResidueNowTime());
                //当前体力当好使用完
                NetContext.getRouter().send(session, response, gatewayAttachment);
                logger.info("[玩家：{}],数据：{}", physicalData.getId(), JsonUtils.object2String(response));
            } else if (physicalReduce < 0) {
                //体力不够用
                logger.error("当前扣除体力值：{},扣除完体力值：{},体力不够用", request.getUsePropNum(), physicalReduce);
                NetContext.getRouter().send(session, Error.valueOf("体力不足"), gatewayAttachment);
            }
        }
    }

    /**
     * 获取体力接口
     */
    @PacketReceiver
    public void atPhysicalPowerRequest(Session session, PhysicalPowerRequest request, GatewayAttachment gatewayAttachment) throws Exception {
        logger.info("[uid:{}] 获取体力 atPhysicalPowerRequest", session.getUid());
        //获取到服务器 数据库存放
        var data =  iPhysicalPowerService.FindOnePhysicalPower(session.getUid());
        if (data == null) {
            var user = userLoginService.LoadPlayerUserEntity(session.getUid());
            if (user == null) {
                logger.error("数据库错误，传递错误uid 错误的[uid:{}] ", session.getUid());
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，传递错误uid"), gatewayAttachment);
                return;
            }
            logger.error("[uid:{}] 获取体力 时,数据库相关不存在，开始创建", session.getUid());
            //体力重新创建出来了，返回出去
            var createData = iPhysicalPowerService.FindOnePhysicalPower(session.getUid());
            if (createData == null) {
                //这种情况一般不会有的，如果有那就rpc通信去创建
                NetContext.getConsumer().syncAsk(CreatePhysicalPowerAsk.ValueOf(user.getPlayerLv(), user.getId()),
                        CreatePhysicalPowerAnswer.class, null).packet();
                /**
                 * 上创建成功了 重新获取
                 */
                createData = iPhysicalPowerService.FindOnePhysicalPower(session.getUid());
            }
            /**
             * 上面rpc 通信 创建 体力失败了 没有创建成功
             * 没有获取到对应
             */
            if (createData == null) {

                logger.error("[uid:{}] 获取体力 时,数据库错误，创建数据错误", session.getUid());
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，创建数据错误，请联系客服"), gatewayAttachment);
                return;
            }
            logger.info("[uid:{}] 获取体力 时,数据库相关不存在，创建成功", session.getUid());
            //有了数据传递过去
            NetContext.getRouter().send(session,
                    PhysicalPowerResponse.ValueOf(
                            createData.getNowPhysicalPowerNum(),
                            createData.getResidueTime(),
                            createData.getMaximumStrength(),
                            createData.getMaximusResidueEndTime(),
                            createData.getResidueNowTime()), gatewayAttachment);

        } else {
            NetContext.getConsumer().syncAsk(RefreshLoginPhysicalPowerNumAsk.ValueOf(session.getUid()), RefreshLoginPhysicalPowerNumAnswer.class, request.getUid())
                    .packet();
            data = iPhysicalPowerService.FindOnePhysicalPower(session.getUid());
            logger.info("[uid:{}] 获取体力 完成", session.getUid());
            //有了数据传递过去
            NetContext.getRouter().send(session, PhysicalPowerResponse.ValueOf(data.getNowPhysicalPowerNum(), data.getResidueTime(),
                    data.getMaximumStrength(), data.getMaximusResidueEndTime(), data.getResidueNowTime()), gatewayAttachment);

        }
    }

    @PacketReceiver
    public void atPhysicalPowerSecondsRequest(Session session, PhysicalPowerSecondsRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("当前请求 PhysicalPowerSecondsRequest [{}]", request.protocolId());

        var nowTimeDown = request.getNowTime() - TimeUtils.now();
        logger.info("UID[{}], 时间差距 {} ms ,{} s", session.getUid(), nowTimeDown, nowTimeDown / 1000);
        boolean isCheating = false;
        if (nowTimeDown / 1000 <= 0) {
            //小于，请求过程中 属于正常的
            isCheating = false;
        } else if (nowTimeDown / 1000 > 0) {
            //比服务器本地时间还要多，代表有问题
            isCheating = true;
        }
        //玩家UID
        var uid = session.getUid();
        if (!isCheating) {
            //正常发请求时间没有错乱，如果时间错乱需要
            NetContext.getConsumer().asyncAsk(RefreshLoginPhysicalPowerNumAsk.ValueOf(uid), RefreshLoginPhysicalPowerNumAnswer.class, uid)
                    .whenComplete(userData -> {
                        //增长体力
                        if (userData.getError() != null) {
                            //失败
                            NetContext.getRouter().send(session, userData.getError(), gatewayAttachment);
                            return;
                        }
                        //rpc 体力缓存已经刷新 返回出去
                        var PhysicalCache = OrmContext.getAccessor().load(session.getUid(), PhysicalPowerEntity.class);
                        if (PhysicalCache != null) {
                            NetContext.getRouter().send(session,
                                    PhysicalPowerSecondsResponse.ValueOf(
                                            PhysicalCache.getNowPhysicalPowerNum(),
                                            PhysicalCache.getResidueTime(),
                                            PhysicalCache.getResidueNowTime(),
                                            PhysicalCache.getMaximumStrength(),
                                            PhysicalCache.getMaximusResidueEndTime()), gatewayAttachment);
                            return;
                        } else {
                            NetContext.getRouter().send(session, Error.valueOf(request, I18nEnum.error_login_process_not.toString()), gatewayAttachment);
                            return;
                        }
                    });

        } else {
            //请求时间出现问题 需要加入黑名单
            NetContext.getRouter().send(session, Error.valueOf(request, "错误时间，请注意"));
        }

    }


}
