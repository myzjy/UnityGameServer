package com.gameServer.singleServer.login.controller;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartRequest;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    public void atLoginTapToStartRequest(Session session, LoginTapToStartRequest request) {
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        var timeEntityList = OrmContext.getAccessor().load(1, AccessGameTimeEntity.class);

    }
}
