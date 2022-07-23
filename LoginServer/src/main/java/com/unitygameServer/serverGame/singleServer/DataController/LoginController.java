package com.unitygameServer.serverGame.singleServer.DataController;

import com.unitygameServer.serverGame.commonRefush.entity.UserEntity;
import com.unitygameServer.serverGame.commonRefush.protocol.login.LoginRequest;
import com.unitygameServer.serverGame.singleServer.model.UserModel;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.model.AttributeType;
import com.zfoo.net.session.model.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.orm.model.cache.IEntityCaches;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.util.math.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Dictionary;
import java.util.Hashtable;

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
            NetContext.getRouter().send(session, Error.valueOf(""));

            return;
        }
//此处为校验敏感词
//        if () {
//
//        }

        var sid=session.getSid();
        EventBus.execute(HashUtils.fnvHash(account)).execute(new Runnable() {
            @Override
            public void run() {
                var userEntity= OrmContext.getAccessor().load(account,UserEntity.class);
            }
        });
    }

    @PacketReceiver
    private void LogoutRequest(Session session, LoginRequest request) {

        //拿到uid
        var uid = (long) session.getAttribute(AttributeType.UID);
        var sid = session.getSid();
//        EventBus.execute(HashUtils.fnvHash())
    }
}
