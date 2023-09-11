package com.gameServer.home.login.controller;

import com.gameServer.common.constant.TankDeployEnum;
import com.gameServer.common.protocol.login.LoginTapToStartRequest;
import com.gameServer.common.protocol.login.LoginTapToStartResponse;
import com.gameServer.common.resource.AccesGameTimeResource;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.anno.StorageAutowired;
import com.zfoo.storage.manager.StorageInt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/2/17 23:39
 */
@Component
public class LoginTapStartController {
    //    private AccessGameTimeEntity timeEntity;
    private static final Logger logger = LoggerFactory.getLogger(LoginTapStartController.class);
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;
    @StorageAutowired
    private StorageInt<Integer, AccesGameTimeResource> accesGameTimeResourceStorage;

    @PacketReceiver
    public void atLoginTapToStartRequest(Session session, LoginTapToStartRequest request, GatewayAttachment gatewayAttachment) throws ParseException {
        logger.info("=============================================");
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        logger.info("=============================================");
        //读取到服务器
        var timeEntityList = accesGameTimeResourceStorage.get(1);
        
        var serverOpenDate = new Date(timeEntityList.getTime());
        var dateTime = TimeUtils.dateToString(serverOpenDate);
        logger.info(dateTime);
        var nowTimeEntity = timeEntityList.getTime();
        if (TimeUtils.now() < nowTimeEntity) {
            logger.info("[服务器开启] 可以开始链接登录");
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("服务器正在开启阶段", true), gatewayAttachment);
        } else {
            logger.info("[关闭服务器时间{}] ", dateTime);
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("服务器已关闭", false), gatewayAttachment);
        }
    }


}
