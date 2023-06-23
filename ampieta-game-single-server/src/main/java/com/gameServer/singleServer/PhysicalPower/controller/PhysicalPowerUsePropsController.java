package com.gameServer.singleServer.PhysicalPower.controller;

import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerRequest;
import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerResponse;
import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerUsePropsRequest;
import com.gameServer.singleServer.PhysicalPower.service.IPhysicalPowerService;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
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
    private IPhysicalPowerService physicalPowerService;

    /**
     * 使用体力的控制 客户端 回调
     */
    @PacketReceiver
    public void atPhysicalPowerUsePropsRequest(Session session, PhysicalPowerUsePropsRequest request) {
        logger.info("[uid:{}] 调用使用体力 开始一张战斗之后就会扣除", session.getUid());
        var physicalData = physicalPowerService.FindOnePhysicalPower(session.getUid());
        var physicalReduce = physicalData.getNowPhysicalPowerNum() - request.getUsePropNum();
        if (physicalReduce >= physicalData.getMaximumStrength()) {
            logger.info("当前扣除体力值：{}，扣除完的体力，依旧满格体力，当前体力：{},扣除完体力值：{}", request.getUsePropNum(), physicalData.getNowPhysicalPowerNum(), physicalReduce);
            physicalData.setNowPhysicalPowerNum(physicalReduce);
            physicalPowerService.UpdatePhysicalPowerEntityOrm(physicalData);
        } else {
            logger.info("当前扣除体力值：{}，扣除体力,当前体力：{},扣除完体力值：{}", request.getUsePropNum(), physicalData.getNowPhysicalPowerNum(), physicalReduce);
            //需要将体力相关修改

        }

    }

    @PacketReceiver
    public void atPhysicalPowerRequest(Session session, PhysicalPowerRequest request) throws NullPointerException {
        logger.info("[uid:{}] 获取体力 atPhysicalPowerRequest", session.getUid());
        //获取到服务器 数据库存放
        var data = OrmContext.getAccessor().load(session.getUid(), PhysicalPowerEntity.class);
        if (data == null) {
            var serverSession = NetContext.getSessionManager().getServerSession(1);
            var user = OrmContext.getAccessor().load(session.getUid(), PlayerUserEntity.class);
            if (user == null) {
                logger.error("数据库错误，传递错误uid 错误的[uid:{}] ", session.getUid());
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，传递错误uid"));
                return;
            }
            logger.error("[uid:{}] 获取体力 时,数据库相关不存在，开始创建", session.getUid());

            //这种情况一般不会有的，如果有那就rpc通信去创建
            NetContext.getRouter().asyncAsk(serverSession,
                    CreatePhysicalPowerAsk.ValueOf(user.getPlayerLv(), user.getId()),
                    CreatePhysicalPowerAnswer.class, null).whenComplete(res -> {
                //体力重新创建出来了，返回出去
                var createData = OrmContext.getAccessor().load(session.getUid(), PhysicalPowerEntity.class);
                if (createData == null) {
                    logger.error("[uid:{}] 获取体力 时,数据库错误，创建数据错误", session.getUid());
                    NetContext.getRouter().send(session, Error.valueOf("数据库错误，创建数据错误，请联系客服"));
                    return;
                }
                logger.info("[uid:{}] 获取体力 时,数据库相关不存在，创建成功", session.getUid());

                //有了数据传递过去
                NetContext.getRouter().send(session, PhysicalPowerResponse.ValueOf(createData.getNowPhysicalPowerNum(), createData.getResidueTime(),
                        createData.getMaximumStrength(), createData.getMaximusResidueEndTime(), createData.getResidueNowTime()));
            });
        } else {
            logger.info("[uid:{}] 获取体力 完成", session.getUid());
            //有了数据传递过去
            NetContext.getRouter().send(session, PhysicalPowerResponse.ValueOf(data.getNowPhysicalPowerNum(), data.getResidueTime(),
                    data.getMaximumStrength(), data.getMaximusResidueEndTime(), data.getResidueNowTime()));
        }
    }
}
