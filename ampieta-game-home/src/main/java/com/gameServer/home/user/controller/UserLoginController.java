package com.gameServer.home.user.controller;

import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.user.GameMainUserInfoToRequest;
import com.gameServer.commonRefush.protocol.user.GameMainUserInfoToResponse;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.event.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.protocol.util.JsonUtils;
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

    @EventReceiver
    public void onCreateOrmAccesTimeEvent(CreateOrmAccesTimeEvent event) {
        if (userLoginService.IsAcesGameTimeResource()) {
            logger.error("未配置服务器开始时间");
            return;
        }
        var dict = userLoginService.GetAccesTimeAll();
        for (var item : dict) {
            var entity = userLoginService.FindAccessGameTimeEntity(item.getTimeID());
            if (entity == null) {
                //数据库没有相关配置
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                userLoginService.InsertAccessGameTimeEntity(entity);
            } else {
                entity = new AccessGameTimeEntity();
                entity.setTimeID(item.getTimeID());
                entity.setTime(new Date(item.getTime()));
                entity.setId(item.getTimeID());
                userLoginService.UpdateAccessGameTimeEntity(entity);
            }
            logger.info("AccesGameTimeResource:{}", JsonUtils.object2String(entity));
        }
    }

    @PacketReceiver
    public void atGameMainUserInfoToRequest(Session session, GameMainUserInfoToRequest request, GatewayAttachment gatewayAttachment) {
        //发消息的
        var uid = session.getUid();
        var userData = userLoginService.LoadPlayerUserEntity(uid);
        if (userData == null) {
            NetContext.getRouter().send(session, Error.valueOf("出现错误，用户信息(id)"), gatewayAttachment);
            return;
        }
        if (request.getUid() > 0) {
            logger.info("请求玩家：{}", request.getUid());
        }
        // var physical = userLoginService.GetToUserIDPhysicalPowerEntity(uid);
        //等级
        var nowLv = userData.getPlayerLv();
        //最大等级
        var maxNowLv = userLoginService.ConfigResourceLength();
        //当前经验
        var exp = userData.getNowExp();
        //当前等就最大经验
        var nowLvMaxExp = userData.getNowLvMaxExp();
        //金币
        var goldCoinNum = userData.getGoldNum();
        //普通钻石
        var diamondsNum = userData.getDiamondNum();
        //付费钻石
        var paidDiamondsNum = userData.getPremiumDiamondNum();
        /**
         * 返回 GameMainUserInfo Response
         */
        var dataResponse = GameMainUserInfoToResponse.ValueOf(nowLv, maxNowLv, exp, nowLvMaxExp, goldCoinNum, diamondsNum, paidDiamondsNum);
        logger.info("GameMainUserInfoToResponse:{}", JsonUtils.object2String(dataResponse));
        NetContext.getRouter().send(session, dataResponse, gatewayAttachment);
    }
}
