package com.gameServer.singleServer.login.controller;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartRequest;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartResponse;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Objects;

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

    @PacketReceiver
    public void atLoginTapToStartRequest(Session session, LoginTapToStartRequest request) throws ParseException {
        logger.info("=============================================");
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        logger.info("=============================================");
        //读取到服务器
        var timeEntityList = OrmContext.getAccessor().load(1, AccessGameTimeEntity.class);
        var dateTime = TimeUtils.timeToString(Objects.requireNonNull(timeEntityList).getTime());
        var time = TimeUtils.dayStringToDate(dateTime);
        logger.info(dateTime);
        var nowTimeEntity = timeEntityList.getTime();
        if (TimeUtils.now() < nowTimeEntity) {
            logger.info("[服务器开启] 可以开始链接登录");
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("", true));
        } else {
            logger.info("[关闭服务器时间{}] ", dateTime);
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("服务器已关闭", false));
        }
    }


}
