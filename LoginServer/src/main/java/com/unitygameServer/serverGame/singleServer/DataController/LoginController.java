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
            var userEntity = OrmContext.getAccessor().load(account, AccountEntity.class);
            if (userEntity == null) {

//                    OrmContext.getOrmManager().getCollection();
                //没找到 生成新的uid uid只会在创建角色了会出现
                var newUID = MongoIdUtils.getIncrementIdFromMongoDefault(UserEntity.class) + 10000000;
                userEntity = AccountEntity.valueOf(account, account, password, newUID);


                //插入数据库
                OrmContext.getAccessor().insert(userEntity);
                UserEntity user=UserEntity.valueOf(newUID, account, TimeUtils.now(), TimeUtils.now());
                user.setToken(getToken(userEntity));
                OrmContext.getAccessor().insert(user);

//                    OrmContext.getAccessor().update(userEntity);
            }
            if (deployEnum == TankDeployEnum.dev) {
                //验证密码
                if (StringUtils.isNotBlank(userEntity.getPassword()) && !userEntity.getPassword().trim().equals(password.trim())) {
                    if (userEntity.getUid() > 0) {
                        logger.info("[password:{}]账号或密码错误", password);
                    } else {
                        logger.info("[uid:{}][password:{}]账号或密码错误", userEntity.getUid(), password);
                    }
                    //给客户端服务器
                    NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_password.toString()));
                    return;
                }
            }
            var uid = userEntity.getUid();

            logger.info("[{}][{}]玩家登录[account:{}][password:{}]", uid, sid, account, password);

            var player = UserModelDict.load(uid);
            player.setLastLoginTime(TimeUtils.now());

            player.sid = sid;
            player.session = session;
//                session
        });
    }

    public static String getToken(AccountEntity account) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 10);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", account.getId()).withClaim("UID", account.getUid()).withClaim("password", account.getPassword());
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(sun.security.x509.X509CertImpl.SIGNATURE));
        logger.info("[token:{}]",token);
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
