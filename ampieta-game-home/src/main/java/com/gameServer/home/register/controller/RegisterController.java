package com.gameServer.home.register.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.entity.AccountEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.LogAnswer;
import com.gameServer.commonRefush.protocol.cache.LogAsk;
import com.gameServer.commonRefush.protocol.login.LogRequest;
import com.gameServer.commonRefush.protocol.login.LogResponse;
import com.gameServer.commonRefush.protocol.register.RegisterRequest;
import com.gameServer.commonRefush.protocol.register.RegisterResponse;
import com.gameServer.commonRefush.util.TokenUtils;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.register.service.IRegisterService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.util.MongoIdUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IUserLoginService iUserLoginService;
    @Autowired
    private IRegisterService iRegisterService;
    @Autowired
    private IPhysicalPowerService physicalPowerService;

    @PacketReceiver
    public void atRegisterRequest(Session session, RegisterRequest request, GatewayAttachment gatewayAttachment) throws Exception {
        var account = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        var affirmPassword = request.getAffirmPassword();
        if (StringUtils.isBlank(account)) {
            logger.error("[account:{}]请输入账号", account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_blank.toString()), gatewayAttachment);
            return;
        }
        if (!password.equals(affirmPassword)) {
            logger.error("[account:{}]密码和确认密码不一致，[password:{}][affirmPassword:{}]", account, password, affirmPassword);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_password_not_affirm.toString()), gatewayAttachment);
            return;
        }
        var accountUser = iRegisterService.LoadAccountEntityString(account);
        if (accountUser != null) {
            logger.error("[account:{}]玩家账号,在数据库中存在，请重新输入", account);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_already_exists.getMessage()), gatewayAttachment);
            return;
        }
        //密码长度是否符合要求
        if (password.length() >= 8 && password.length() <= 16) {
            //这个地方可能是显示问题
            if (password.contains(" ")) {
                logger.error("[account:{}]玩家，密码包含空字符，请重新输入,[password:{}]", account, password);
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_password_not_have_null.getMessage()), gatewayAttachment);
                return;
            }
        } else {
            logger.error("[account:{}]玩家，密码长度不符合要求,[password:{}]", account, password);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_password_length.getMessage()), gatewayAttachment);
            return;
        }
        //创建账号 往数据库里保存
        //没找到 生成新的uid uid只会在创建角色了会出现
        var newUID = MongoIdUtils.getIncrementIdFromMongoDefault(PlayerUserEntity.class) + 10000000;
        logger.info("[UID:{}],[{sid:{}}]", newUID, session.getSid());
        var user = iUserLoginService.LoadPlayerUserEntity(newUID);
        //判断当前UID能不能找到对应
        if (user == null) {
            logger.error("[UID:{}]数据库中找不到,开始创建新的玩家数据", newUID);
            //名字先不取
            accountUser = AccountEntity.valueOf(account, account, password, newUID);
            logger.info("创建的玩家数据：[accountUser:{}]", accountUser);
            //插入数据库
            iRegisterService.InsterAccountEntityOrm(accountUser);
            logger.info("创建的玩家数据：[accountUser:{}]成功", accountUser);
        }
        session.setUid(newUID);
        var token = TokenUtils.set(newUID);
        //用户名字我们先以玩家加uid 赋一个初始值
        String userName = StringUtils.format("玩家UID:{}", newUID);
        PlayerUserEntity userEntity = PlayerUserEntity.valueOf(newUID, userName, TimeUtils.now(), TimeUtils.now(),
                                                               token, 0, 0, 0, 0,
                                                               0, 0, 0, 1);
        userEntity.setToken(TokenUtils.set(newUID));
        session.setUid(newUID);
        logger.info("[Token:{}]", userEntity.getToken());
        //插入数据了，就代表注册成功了
        iUserLoginService.InsertPlayerUserEntity(userEntity);
        //需保证第一个链接服务器的必须是服务器内客户端
        //必须保证万无一失 rpc请求
        physicalPowerService.CreatePhysicalPower(userEntity.getPlayerLv(), userEntity.getId());

        logger.info("[uid:{}] 玩家体力数据创建成功", userEntity.getId());
        NetContext.getRouter().send(session, RegisterResponse.valueOf(true, "ok"), gatewayAttachment);
    }

    @PacketReceiver
    public void atLogAsk(Session session, LogAsk ask) {
        logger.info("[{}]", ask);
        logger.info("{}", session);
        NetContext.getRouter().send(session, new LogAnswer());
    }

    @PacketReceiver
    public void atLogRequest(Session session, LogRequest request) {
        var serverSession = NetContext.getSessionManager().getServerSession(1);
        logger.info("{}", serverSession);
        NetContext.getRouter().
                  asyncAsk(serverSession, new LogAsk(), LogAnswer.class, null).whenComplete(res -> {
                      logger.info("回到");
                      NetContext.getRouter().send(session, new LogResponse());
                  });
    }
}
