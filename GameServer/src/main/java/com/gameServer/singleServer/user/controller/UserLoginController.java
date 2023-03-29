package com.gameServer.singleServer.user.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/3/29 00 46
 */
@Component
public class UserLoginController {
    //log文件
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);


    /**
     * 用户数据
     */
    @EntityCachesInjection
    private IEntityCaches<Long, PlayerUserEntity> UserModelDict;

    @PacketReceiver
    public void atRefreshLoginPhysicalPowerNumAsk(Session session, RefreshLoginPhysicalPowerNumAsk numAsk) {
        var userId = numAsk.getUserId();
        var userEntity = UserModelDict.load(userId);
        if (userEntity.getId() == 0L) {
            NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(Error.valueOf(numAsk, I18nEnum.error_account_not_exit.toString())));
            return;
        }
        NetContext.getRouter().send(session, RefreshLoginPhysicalPowerNumAnswer.ValueOf(userEntity));
    }
}