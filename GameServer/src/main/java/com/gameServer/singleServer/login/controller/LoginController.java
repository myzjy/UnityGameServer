package com.gameServer.singleServer.login.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccountEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAnswer;
import com.gameServer.commonRefush.protocol.cache.refresh.RefreshLoginPhysicalPowerNumAsk;
import com.gameServer.commonRefush.protocol.login.GetPlayerInfoRequest;
import com.gameServer.commonRefush.protocol.login.LoginRequest;
import com.gameServer.commonRefush.protocol.login.LoginResponse;
import com.gameServer.commonRefush.protocol.login.LogoutRequest;
import com.gameServer.commonRefush.util.TokenUtils;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.packet.common.Ping;
import com.zfoo.net.packet.common.Pong;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.util.math.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 登录控制器
 *
 * @author zjy
 * @version 0.1
 * @since 11:23 下午
 */
@Component
public class LoginController {
    //log文件
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * 用户数据
     */
    @EntityCachesInjection
    private IEntityCaches<Long, PlayerUserEntity> UserModelDict;
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;

    /**
     * @apiNote 登录调用
     */
    @PacketReceiver
    public void atLoginRequest(Session session, LoginRequest request) {
        var account = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        if (StringUtils.isBlank(account)) {
            logger.error("[{}] 账号为空", session.getSid());
            //传递过来的账号不对
            //信息传递给客户端
            NetContext.getRouter().send(session, Error.valueOf(request.protocolId(), 0, I18nEnum.error_account_password.getMessage()));
            return;
        }

        var sid = session.getSid();
        {
            var accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
            if (accountUser == null) {
                logger.error("[account：{}，玩家登录]登录时间{}[error:{}]", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), I18nEnum.error_account_not_exit.getMessage());

                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                return;
            }
            //验证密码
            if (StringUtils.isNotBlank(accountUser.getPassword()) && !accountUser.getPassword().trim().equals(password.trim())) {
                if (accountUser.getUid() > 0) {
                    logger.info("[password:{}]账号或密码错误", password);
                } else {
                    logger.info("[uid:{}][password:{}]账号或密码错误", accountUser.getUid(), password);
                }
                logger.error("[UID:{}],Error{}", accountUser.getUid(), I18nEnum.error_account_password.toString());
                //给客户端服务器
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_password.toString()));
                return;
            }
        }
        EventBus.execute(HashUtils.fnvHash(account), () -> {
            //数据库拿去
            AccountEntity accountUser;
            accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
            if (accountUser == null) {
                logger.error("[account：{}，玩家登录]登录时间{}[error:{}]", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), I18nEnum.error_account_not_exit.getMessage());

                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                return;
            }
            //log
            logger.info("[{}玩家登录]登录时间{}", accountUser.getAccount(), TimeUtils.dateFormatForDayTimeString(TimeUtils.now()));
            {
                //通过UID获取
                var user = OrmContext.getAccessor().load(accountUser.getUid(), PlayerUserEntity.class);
                if (user == null) {
                    //必须保证账号存在
                    NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                    return;
                }
                session.setUid(user.getId());
                NetContext.getConsumer().asyncAsk(RefreshLoginPhysicalPowerNumAsk.ValueOf(user.getId()), RefreshLoginPhysicalPowerNumAnswer.class, user.getId())
                        .whenComplete(userData -> {
                            if (userData.getError() != null) {
                                NetContext.getRouter().send(session, userData.getError());
                                return;
                            }

                            var userCache = userData.getPlayerUserEntity();
                            if (userCache.getToken() == null) {
                                logger.info("[当前 uid:{}] 开始获取token", userCache.getId());
                                //没有Token,获取token
                                var token = TokenUtils.set(userCache.getId());
                                logger.info("[当前 uid:{}][新token：{}]", userCache.getId(), token);
                                userCache.setToken(token);
                            }
                            OrmContext.getAccessor().update(userCache);

                        });
                user = OrmContext.getAccessor().load(accountUser.getUid(), PlayerUserEntity.class);
//                if (user.getToken() == null) {
//                    var token = TokenUtils.set(user.getId());
//                    logger.info("[当前 uid:{}][新token：{}]", user.getId(), token);
//                    user.setToken(token);
//                } else {
                var tokenTriple = TokenUtils.get(user.getToken());
                var expirationTimeLong = tokenTriple.getRight();
                var nowLong = TimeUtils.now();
                logger.info("当前token：{},[当前uid：{}],[当前sid：{}]", tokenTriple, session.getUid(), session.getSid());
                logger.info("[expirationTimeLong:{}],[nowLog:{}][当前token是否过期：{}]", TimeUtils.timeToString(expirationTimeLong), TimeUtils.timeToString(nowLong), nowLong > expirationTimeLong);
                if (nowLong > expirationTimeLong) {
                    //代表过时的token
                    var token = TokenUtils.set(user.getId());
                    logger.info("[{}]重新设置Token:[{}]", user.getId(), token);
                    user.setToken(token);
                }
//                }
                //覆盖登录时间
                user = PlayerUserEntity.valueOf(user.getId(), user.getName(), TimeUtils.now(), user.getRegisterTime(),
                        user.getToken(), user.getGoldNum(), user.getPremiumDiamondNum(), user.getDiamondNum(), user.getEndLoginOutTime(),
                        user.getNowExp(), user.getNowPhysicalPowerNum(), user.getNowLvMaxExp(), user.getPlayerLv());
                logger.info("[{}][{}]新得玩家登录数据[UserData:{}]", user.getId(), sid, user.toString());
                OrmContext.getAccessor().update(user);
                logger.info("[{}][{}]数据库刷新成功", user.getId(), sid);
            }

            var uid = accountUser.getUid();

            logger.info("[uid：{}][sid：{}]玩家登录[account:{}][password:{}]", uid, sid, account, password);
            session.setUid(accountUser.getUid());
            //之前插入过数据库，现在是获取
            var player = UserModelDict.load(uid);
            player.setLastLoginTime(TimeUtils.now());

            //设置临时值 sid session
            player.sid = sid;
            player.session = session;
            //消息发送？
            session.setUid(uid);

            //更新
            UserModelDict.update(player);

            //获取的玩家 uid小于0
            if (player.getId() <= 0) {
                logger.error("[玩家当前uid:{}][sid：{}],错误值，请检查", player.getId(), session.getSid());
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                return;
            }
//            EventBus.asyncSubmit(StartLoginBagEvent.ValueOf(session));
            //返回数据
            NetContext.getRouter().send(session, LoginResponse.valueOf(player.getToken(), player.getName(), player.id(), player.getGoldNum(), player.getPremiumDiamondNum(), player.getDiamondNum()));
        });
    }

    @PacketReceiver
    public void atGetPlayerInfoRequest(Session session, GetPlayerInfoRequest request) {
        var token = request.getToken();
        if (StringUtils.isBlank(token)) {
            logger.info("[sid:{}]token传递空值，token:[{}]", session.getSid(), token);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_protocol_param.toString()));
            return;
        }
        //解析token
        var triple = TokenUtils.get(token);
        var uid = triple.getLeft();
        var sid = session.getSid();

        logger.info("[{}][{}]玩家信息[token:{}]", uid, sid, token);

        PlayerUserEntity userEntity = OrmContext.getAccessor().load(uid, PlayerUserEntity.class);
        var player = UserModelDict.load(uid);
        // 设置session
        player.sid = sid;
        player.session = session;
        session.setUid(uid);
        if (userEntity == null) {
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_user_orm_no_uid.toString()));
            logger.info("[{}][{}]玩家信息不存在与数据库有中，token:[{}]", uid, sid, token);
            return;
        }

        NetContext.getRouter().send(session, LoginResponse.valueOf(token, userEntity.getName(), userEntity.id(), userEntity.getGoldNum(), userEntity.getPremiumDiamondNum(), userEntity.getDiamondNum()));
    }


    @PacketReceiver
    public void atLogoutRequest(Session session, LogoutRequest request) {
        //拿到uid
        var uid = session.getUid();
        var sid = session.getSid();
        logger.info("[{}][{}]玩家退出游戏", uid, sid);

        var player = UserModelDict.load(uid);
        player.sid = 0;
        player.session = null;
        logger.info("[退出游戏] 开始更新玩家退出游戏时间");
        player.setEndLoginOutTime(TimeUtils.now());
        OrmContext.getAccessor().update(player);
        logger.info("[退出游戏] 结束更新玩家退出游戏时间");
        UserModelDict.update(player);
    }

    @PacketReceiver
    public void atPing(Session session, Ping request) {
        NetContext.getRouter().send(session, Pong.valueOf(TimeUtils.now()));
    }
}
