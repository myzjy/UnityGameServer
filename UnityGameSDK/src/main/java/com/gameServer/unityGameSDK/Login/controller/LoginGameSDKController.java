package com.gameServer.unityGameSDK.Login.controller;

import com.gameServer.common.constant.I18nEnum;
import com.gameServer.common.constant.TankDeployEnum;
import com.gameServer.common.entity.AccountEntity;
import com.gameServer.common.protocol.sdk.login.SDKLoginRequest;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 登陆sdk
 *
 * @author zjy
 * @version 1.0
 * @since 2023/5/6 17 59
 */
@Component
public class LoginGameSDKController {
    private static final Logger logger = LoggerFactory.getLogger(LoginGameSDKController.class);
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;

    @PacketReceiver
    public void atLoginGameSDKRequest(Session session, SDKLoginRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("SDK 登陆 游戏内");
        logger.info("[玩家：{}]", request.getAccount());
        logger.info("[ip:{}] 调用sdk登陆", session.getChannel().remoteAddress().toString());
        var account = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        if (StringUtils.isBlank(account)) {
            logger.error("[{}] 账号为空", session.getSid());
            //传递过来的账号不对
            //信息传递给客户端
            NetContext.getRouter().send(session, Error.valueOf(request.protocolId(), 0, I18nEnum.error_account_password.getMessage()), gatewayAttachment);
            return;
        }
        var accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
        if (accountUser == null) {
            logger.error("[account：{}，玩家登录][登录时间{}][error:{}]", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), I18nEnum.error_account_not_exit.getMessage());

            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()), gatewayAttachment);
            return;
        }
        logger.info("[account:{}],[uid:{}],", account, accountUser.getUid());

    }

}
