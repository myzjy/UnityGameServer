package com.unitygameServer.serverGame.singleServer.DataController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.unitygameServer.serverGame.commonRefush.constant.I18nEnum;
import com.unitygameServer.serverGame.commonRefush.constant.TankDeployEnum;
import com.unitygameServer.serverGame.commonRefush.entity.AccountEntity;
import com.unitygameServer.serverGame.commonRefush.entity.UserEntity;
import com.unitygameServer.serverGame.commonRefush.protocol.login.LoginRequest;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.orm.model.cache.IEntityCaches;
import com.zfoo.orm.util.MongoIdUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.util.math.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;


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
    private IEntityCaches<Long, UserEntity> UserModelDict;
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
            if (accountUser == null) {
                //没找到 生成新的uid uid只会在创建角色了会出现
                var newUID = MongoIdUtils.getIncrementIdFromMongoDefault(UserEntity.class) + 10000000;
                var user = OrmContext.getAccessor().load(newUID, UserEntity.class);
                //判断当前UID能不能找到对应
                if (user == null) {
                    accountUser = AccountEntity.valueOf(account, account, password, newUID);
                } else {
                    newUID += 1;
                    user = OrmContext.getAccessor().load(newUID, UserEntity.class);
                    while (user == null) {
                        newUID += 1;
                        user = OrmContext.getAccessor().load(newUID, UserEntity.class);
                    }
                }


                //插入数据库
                OrmContext.getAccessor().insert(accountUser);
                UserEntity userEntity = UserEntity.valueOf(newUID, account, TimeUtils.now(), TimeUtils.now());
                userEntity.setToken(getToken(accountUser));
                OrmContext.getAccessor().insert(userEntity);

            }
            {

                //通过UID获取
                var user = OrmContext.getAccessor().load(accountUser.getUid(), UserEntity.class);
                assert user != null;
                //覆盖登录时间
                user = UserEntity.valueOf(user.getId(), accountUser.getName(), TimeUtils.now(), user.getRegisterTime());
                OrmContext.getAccessor().update(user);
            }
            if (deployEnum == TankDeployEnum.dev) {
                //验证密码
                if (StringUtils.isNotBlank(accountUser.getPassword()) && !accountUser.getPassword().trim().equals(password.trim())) {
                    if (accountUser.getUid() > 0) {
                        logger.info("[password:{}]账号或密码错误", password);
                    } else {
                        logger.info("[uid:{}][password:{}]账号或密码错误", accountUser.getUid(), password);
                    }
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
            if(player.getId()<=0){
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
                return;
            }


//                session
        });
    }

    public static String getToken(AccountEntity account) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 10);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", account.getId()).withClaim("UID", account.getUid()).withClaim("password", account.getPassword());
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256("signature"));
        logger.info("[token:{}]", token);
        return token;
    }

//    @PacketReceiver
//    private void LogoutRequest(Session session, LoginRequest request) {
//
//        //拿到uid
//        var uid = (long) session.getAttribute(AttributeType.UID);
//        var sid = session.getSid();
////        EventBus.execute(HashUtils.fnvHash())
//    }
}
