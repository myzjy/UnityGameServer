package com.gameServer.singleServer.register.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.AccountEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.register.RegisterRequest;
import com.gameServer.commonRefush.protocol.register.RegisterResponse;
import com.gameServer.commonRefush.util.TokenUtils;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.util.MongoIdUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 注册接口
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/11/3 23:29
 */
@Component
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @PacketReceiver
    public void atRegisterRequest(Session session, RegisterRequest request) {
        var account = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        var affirmPassword = request.getAffirmPassword();
        if (StringUtils.isBlank(account)) {
            logger.error("[time:{}][{}]请输入账号", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_blank.toString()));
            return;
        }
        if (!password.equals(affirmPassword)) {
            logger.error("[time:{}][{}]密码和账号不一样", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_password_not_affirm.toString()));
            return;
        }
        var accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
        if (accountUser != null) {
            logger.error("[time:{}][{}]玩家账号,在数据库中存在，请重新输入", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_already_exists.toString()));
            return;
        }
        //密码长度是否符合要求
        if (password.length() >= 8 && password.length() <= 16) {
            //这个地方可能是显示问题
            if (password.contains(" ")) {
                logger.error("[time:{}][{}]玩家，密码包含空字符，请重新输入", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), account);
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_password_not_have_null.toString()));
                return;
            }
        } else {
            logger.error("[time:{}][{}]玩家，密码长度不符合要求", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_password_length.toString()));
            return;
        }

        //创建账号 往数据库里保存
        //没找到 生成新的uid uid只会在创建角色了会出现
        var newUID = MongoIdUtils.getIncrementIdFromMongoDefault(PlayerUserEntity.class) + 10000000;
        logger.info("[UID:{}],[{sid:{}}]", newUID, session.getSid());
        var user = OrmContext.getAccessor().load(newUID, PlayerUserEntity.class);
        //判断当前UID能不能找到对应
        if (user == null) {
            logger.error("[time:{}],[UID{}]数据库中找不到,开始创建新的玩家数据", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), newUID);
            //名字先不取
            accountUser = AccountEntity.valueOf(account, account, password, newUID);
            logger.info("[time:{}],创建的玩家数据：[{}]", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), accountUser);

            //插入数据库
            OrmContext.getAccessor().insert(accountUser);
            logger.info("[time:{}],创建的玩家数据：[{}]成功", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), accountUser);
        }
        session.setUid(newUID);

        var token = TokenUtils.set(newUID);
        //用户名字我们先以玩家加uid 赋一个初始值
        String userName = StringUtils.format("玩家{}", newUID);
        PlayerUserEntity userEntity = PlayerUserEntity.valueOf(newUID, userName, TimeUtils.now(), TimeUtils.now(), token);
        userEntity.setToken(TokenUtils.set(newUID));
        logger.info("[time:{}],[Token:{}]", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), userEntity.getToken());
        //插入数据了，就代表注册成功了
        OrmContext.getAccessor().insert(userEntity);
        NetContext.getRouter().send(session, RegisterResponse.valueOf(true, "ok"));

    }

}
