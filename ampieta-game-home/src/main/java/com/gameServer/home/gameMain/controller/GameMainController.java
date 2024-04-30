package com.gameServer.home.gameMain.controller;

import com.gameServer.common.protocol.gameMain.OpenGameMainUIPanelRequest;
import com.gameServer.common.protocol.physicalPower.PhysicalPowerSecondsResponse;
import com.gameServer.common.protocol.playerUser.UserMsgInfoData;
import com.gameServer.common.protocol.resources.GameMainUserResourcesResponse;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.protocol.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/4/25 00 02
 */
@Component
public class GameMainController {
    private static final Logger logger = LoggerFactory.getLogger(GameMainController.class);
    @Autowired
    private IPhysicalPowerService physicalPowerService;
    @Autowired
    private IUserLoginService userLoginService;

    @PacketReceiver
    public void atOpenGameMainUIPanelRequest(Session session, OpenGameMainUIPanelRequest request, GatewayAttachment attachment) {
        logger.info("调用协议号：1049");
        var uid = session.getUid();
        // 返回 角色 相关信息
        // 返回 资源相关信息
        var userCache = userLoginService.LoadPlayerUserEntity(uid);
        var userMsgInfoData = UserMsgInfoData.valueOf();
        userMsgInfoData.setUserName(userCache.getName());
        userMsgInfoData.setExp(userCache.getNowExp());
        userMsgInfoData.setMaxExp(userCache.getNowLvMaxExp());
        userMsgInfoData.setLv(userCache.getPlayerLv());
        userMsgInfoData.setMaxLv(userLoginService.ConfigResourceLength());
        userMsgInfoData.setDiamondNum(userCache.getDiamondNum());
        userMsgInfoData.setGoldNum(userCache.getGoldNum());
        userMsgInfoData.setPremiumDiamondNum(userCache.getPremiumDiamondNum());
        var response = GameMainUserResourcesResponse.valueOf();
        response.setUserMsgInfoData(userMsgInfoData);
        NetContext.getRouter().send(session, response, attachment);
        //返回体力相关
        //正常发请求时间没有错乱，如果时间错乱需要
        physicalPowerService.RefreshLoginPhysicalPower(session.getUid());
        // 体力缓存已经刷新 返回出去
        var PhysicalCache = physicalPowerService.FindOnePhysicalPower(session.getUid());
        var dataResponse = PhysicalPowerSecondsResponse.ValueOf(
                PhysicalCache.getNowPhysicalPowerNum(),
                PhysicalCache.getResidueTime(),
                PhysicalCache.getResidueNowTime(),
                PhysicalCache.getMaximumStrength(),
                PhysicalCache.getMaximusResidueEndTime());
        logger.info("PhysicalPowerSecondsResponse:{}", JsonUtils.object2String(dataResponse));
        NetContext.getRouter().send(session, dataResponse, attachment);
    }
}
