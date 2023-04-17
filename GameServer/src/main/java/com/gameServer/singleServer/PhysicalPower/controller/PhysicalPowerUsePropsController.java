package com.gameServer.singleServer.PhysicalPower.controller;

import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerUsePropsRequest;
import com.gameServer.singleServer.PhysicalPower.service.IPhysicalPowerService;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
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
}
