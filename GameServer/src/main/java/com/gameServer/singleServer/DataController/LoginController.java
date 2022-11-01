package com.gameServer.singleServer.DataController;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccountEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
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
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.orm.util.MongoIdUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.util.math.HashUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
            //传递过来的账号不对
            //信息传递给客户端
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
            return;
        }

        var sid = session.getSid();
        EventBus.execute(HashUtils.fnvHash(account), () -> {
            //数据库拿去
            var accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
            //log
            logger.info("[{}玩家登录]登录时间{}", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()));
            if (accountUser == null) {
                //没找到 生成新的uid uid只会在创建角色了会出现
                var newUID = MongoIdUtils.getIncrementIdFromMongoDefault(PlayerUserEntity.class) + 10000000;
                var user = OrmContext.getAccessor().load(newUID, PlayerUserEntity.class);
                //判断当前UID能不能找到对应
                if (user == null) {
                    logger.error("[time:{}],[UID{}]数据库中找不到,开始创建新的玩家数据", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), newUID);
                    //名字先不取
                    accountUser = AccountEntity.valueOf(account, account, password, newUID);
                    logger.info("[time:{}],创建的玩家数据：[{}]", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), accountUser.toString());

                    //插入数据库
                    OrmContext.getAccessor().insert(accountUser);
                    logger.info("[time:{}],创建的玩家数据：[{}]成功", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), accountUser.toString());
                }


                var token = TokenUtils.set(newUID);
                PlayerUserEntity userEntity = PlayerUserEntity.valueOf(newUID, "", TimeUtils.now(), TimeUtils.now(), token);
                userEntity.setToken(TokenUtils.set(newUID));
                logger.info("[time:{}],[Token:{}]", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), userEntity.getToken());

                OrmContext.getAccessor().insert(userEntity);

            }
            {

                //通过UID获取
                var user = OrmContext.getAccessor().load(accountUser.getUid(), PlayerUserEntity.class);
                if (user == null) {
                    //流程卡住
                    var token = TokenUtils.set(accountUser.getUid());
                    PlayerUserEntity userEntity = PlayerUserEntity.valueOf(accountUser.getUid(), "", TimeUtils.now(), TimeUtils.now(), token);
                    userEntity.setToken(TokenUtils.set(accountUser.getUid()));
                    logger.info("[time:{}][UID:{}],[Token:{}]", TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), userEntity.getId(), userEntity.getToken());
                    OrmContext.getAccessor().insert(userEntity);
                }
                assert user != null;
                if (user.getToken() == null) {
                    var token = TokenUtils.set(user.getId());
                    logger.info("[{}][{}]", user.getId(), token);

                    user.setToken(token);
                } else {
                    var tokenTriple = TokenUtils.get(user.getToken());
                    var expirationTimeLong = tokenTriple.getRight();
                    var nowLong = TimeUtils.now();
                    if (nowLong > expirationTimeLong) {
                        //代表过时的token
                        var token = TokenUtils.set(user.getId());
                        logger.info("[{}]重新设置Token:[{}]", user.getId(), token);
                        user.setToken(token);
                    }
                }
                //覆盖登录时间
                user = PlayerUserEntity.valueOf(user.getId(), user.getName(), TimeUtils.now(), user.getRegisterTime(), user.getToken());
                logger.info("[{}][{}]新得玩家登录数据[UserData:{}]", user.getId(), sid, user.toString());
                OrmContext.getAccessor().update(user);
                logger.info("[{}][{}]数据库刷新成功", user.getId(), sid);
            }
            if (deployEnum == TankDeployEnum.dev) {
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
            var uid = accountUser.getUid();

            logger.info("[{}][{}]玩家登录[account:{}][password:{}]", uid, sid, account, password);
            session.putAttribute(AttributeType.UID, accountUser.getUid());
            //之前插入过数据库，现在是获取
            var player = UserModelDict.load(uid);
            player.setLastLoginTime(TimeUtils.now());

            //设置临时值 sid session
            player.sid = sid;
            player.session = session;
            //消息发送？
            session.putAttribute(AttributeType.UID, uid);

            //更新
            UserModelDict.update(player);

            //获取的玩家 uid小于0
            if (player.getId() <= 0) {
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                return;
            }
            //返回数据
            NetContext.getRouter().send(session, LoginResponse.valueOf(player.getToken(), player.getName(), player.id()));
        });
    }

    @PacketReceiver
    public void atGetPlayerInfoRequest(Session session, GetPlayerInfoRequest request) {
        var token = request.getToken();
        if (StringUtils.isBlank(token)) {
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_protocol_param.toString()));
            logger.info("[{}]token传递空值，token:[{}]", session.getSid(), token);
            return;
        }
        //解析token
        var triple = TokenUtils.get(token);
        var uid = triple.getLeft();
        var sid = session.getSid();

        logger.info("c[{}][{}]玩家信息[token:{}]", uid, sid, token);

        PlayerUserEntity userEntity = OrmContext.getAccessor().load(uid, PlayerUserEntity.class);
        var player = UserModelDict.load(uid);
        // 设置session
        player.sid = sid;
        player.session = session;
        session.putAttribute(AttributeType.UID, uid);
        if (userEntity == null) {
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_user_orm_no_uid.toString()));
            logger.info("[{}][{}]玩家信息不存在与数据库有中，token:[{}]", uid, sid, token);
            return;
        }

        NetContext.getRouter().send(session, LoginResponse.valueOf(token, userEntity.getName(), userEntity.id()));
    }


    @PacketReceiver
    public void atLogoutRequest(Session session, LogoutRequest request) {
        //拿到uid
        var uid = (long) session.getAttribute(AttributeType.UID);
        var sid = session.getSid();
        logger.info("[{}][{}]玩家退出游戏", uid, sid);

        var player = UserModelDict.load(uid);
        player.sid = 0;
        player.session = null;
        UserModelDict.update(player);
    }

    @PacketReceiver
    public void atPing(Session session, Ping request) {
        NetContext.getRouter().send(session, Pong.valueOf(TimeUtils.now()));
    }
}