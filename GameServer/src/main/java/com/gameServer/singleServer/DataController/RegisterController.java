package com.gameServer.singleServer.DataController;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.protocol.register.RegisterRequest;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.Session;
import com.zfoo.protocol.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * 注册接口
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/11/3 23:29
 */
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;

    @PacketReceiver
    public void atRegisterRequest(Session session, RegisterRequest request) {
        var acount = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        var affirmPassword = request.getAffirmPassword();
        if(StringUtils.isBlank(acount)){
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
            return;
        }
    }

}
