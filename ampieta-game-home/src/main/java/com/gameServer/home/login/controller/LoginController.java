package com.gameServer.home.login.controller;

import com.gameServer.commonRefush.constant.I18nEnum;
import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccountEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.protocol.login.GetPlayerInfoRequest;
import com.gameServer.commonRefush.protocol.login.LoginRequest;
import com.gameServer.commonRefush.protocol.login.LoginResponse;
import com.gameServer.commonRefush.protocol.login.LogoutRequest;
import com.gameServer.commonRefush.resource.AccesGameTimeResource;
import com.gameServer.commonRefush.resource.ConfigResource;
import com.gameServer.commonRefush.util.TokenUtils;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.event.manager.EventBus;
import com.zfoo.net.NetContext;
import com.zfoo.net.core.gateway.model.AuthUidToGatewayCheck;
import com.zfoo.net.core.gateway.model.AuthUidToGatewayConfirm;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.packet.common.Ping;
import com.zfoo.net.packet.common.Pong;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.orm.cache.IEntityCaches;
import com.zfoo.orm.model.anno.EntityCachesInjection;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import com.zfoo.util.math.HashUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IUserLoginService userLoginService;
    @Autowired
    private IPhysicalPowerService physicalPowerService;
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;

    /**
     * @apiNote 登录调用
     */
    @PacketReceiver
    public void atLoginRequest(Session session, LoginRequest request, GatewayAttachment gatewayAttachment) {
        logger.info("[ip:{}] 调用登陆，还并未被赋值uid", session.getChannel().remoteAddress().toString());
        var account = StringUtils.trim(request.getAccount());
        var password = request.getPassword();
        if (StringUtils.isBlank(account)) {
            logger.error("[{}] 账号为空", session.getSid());
            //传递过来的账号不对
            //信息传递给客户端
            NetContext.getRouter().send(session, Error.valueOf(request.protocolId(), 0, I18nEnum.error_account_password.getMessage()), gatewayAttachment);
            return;
        }
        var sid = session.getSid();
        {
            var accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
            if (accountUser == null) {
                logger.error("[account：{}，玩家登录]登录时间{}[error:{}]", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), I18nEnum.error_account_not_exit.getMessage());
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()), gatewayAttachment);
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
                NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_password.toString()), gatewayAttachment);
                return;
            }
        }
        //数据库拿去
        AccountEntity accountUser = OrmContext.getAccessor().load(account, AccountEntity.class);
        if (accountUser == null) {
            logger.error("[account：{}，玩家登录]登录时间{}[error:{}]", account, TimeUtils.dateFormatForDayTimeString(TimeUtils.now()), I18nEnum.error_account_not_exit.getMessage());
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()), gatewayAttachment);
            return;
        }
        NetContext.getRouter().send(session, AuthUidToGatewayCheck.valueOf(accountUser.getUid()), gatewayAttachment);
    }

    @PacketReceiver
    public void atAuthUidToGatewayConfirm(Session session, AuthUidToGatewayConfirm confirm, GatewayAttachment gatewayAttachment) throws Exception {
        var uid = confirm.getUid();
        var sid = gatewayAttachment.getSid();
        if (uid <= 0) {
            logger.error("授权[uid:{}]异常", uid);
            return;
        }
        //通过UID获取
        var userCache = userLoginService.LoadPlayerUserEntity(uid);
        if (userCache == null) {
            logger.error("发送过来 [uid:{}] 数据库不存在相关人物，请注意！！！！！！！", uid);
            //必须保证账号存在
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()), gatewayAttachment);
            return;
        }
        //获取的玩家 uid小于0
        if (userCache.getId() <= 0) {
            logger.error("[玩家当前uid:{}][sid：{}],错误值，请检查", userCache.getId(), session.getSid());
            userLoginService.UpdatePlayerUserEntity(userCache);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
            return;
        }
        var data = userLoginService.GetConfigResourceData(userCache.getPlayerLv());
        session.setUid(userCache.getId());
        var powerData = physicalPowerService.FindOnePhysicalPower(session.getUid());
        if (powerData == null) {
            logger.error("[uid:{}] 刷新 玩家自己体力 缓存数据库 出现错误,error:{}", uid, I18nEnum.error_login_process_not.toString());
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_login_process_not.toString()), gatewayAttachment);
            return;
        }
        physicalPowerService.RefreshLoginPhysicalPower(session.getUid());

        //以防测试期间出现问题
        if (userCache.getToken() == null) {
            logger.info("[当前 uid:{}] 开始获取token", userCache.getId());
            //没有Token,获取token
            var token = TokenUtils.set(userCache.getId());
            logger.info("[当前 uid:{}][新token：{}]", userCache.getId(), token);
            userCache.setToken(token);
        }
        userLoginService.UpdatePlayerUserEntity(userCache);

        //防止token 过时
        var tokenTriple = TokenUtils.get(userCache.getToken());
        var expirationTimeLong = tokenTriple.getRight();
        var nowLong = TimeUtils.now();
        logger.info("当前token：{},[当前uid：{}],[当前sid：{}]", tokenTriple, session.getUid(), session.getSid());
        logger.info("[expirationTimeLong:{}],[nowLog:{}][当前token是否过期：{}]", TimeUtils.timeToString(expirationTimeLong), TimeUtils.timeToString(nowLong), nowLong > expirationTimeLong);
        if (nowLong > expirationTimeLong) {
            //代表过时的token
            var token = TokenUtils.set(userCache.getId());
            logger.info("[{}]重新设置Token:[{}]", userCache.getId(), token);
            userCache.setToken(token);
        }
        userCache.setNowLvMaxExp(data.getMaxExp());
        //覆盖登录时间
        userCache = PlayerUserEntity.valueOf(userCache.getId(),
                                        userCache.getName(),
                                        TimeUtils.now(),
                                        userCache.getRegisterTime(),
                                        userCache.getToken(),
                                        userCache.getGoldNum(),
                                        userCache.getPremiumDiamondNum(),
                                        userCache.getDiamondNum(),
                                        userCache.getEndLoginOutTime(),
                                        userCache.getNowExp(),
                                        userCache.getNowPhysicalPowerNum(),
                                        userCache.getNowLvMaxExp(),
                                        userCache.getPlayerLv());
        userCache.setLastLoginTime(TimeUtils.now());
        logger.info("[{}][{}]创建最新玩家登录数据 更新数据库", userCache.getId(), sid);
        userCache.sid = sid;
        userCache.session = session;
        logger.info("[玩家{}] 更新 玩家数据缓存 赋值 session sid", uid);
        logger.info("[{}][{}]数据库刷新成功", userCache.getId(), sid);

        userLoginService.UpdatePlayerUserEntity(userCache);
        //返回数据
        NetContext.getRouter().send(session,
                                    LoginResponse.valueOf(userCache.getToken(),
                                                          userCache.getName(),
                                                          userCache.id(),
                                                          userCache.getGoldNum(),
                                                          userCache.getPremiumDiamondNum(),
                                                          userCache.getDiamondNum(),
                                                          userCache.getPlayerLv(),
                                                          userCache.getNowExp(),
                                                          userLoginService.ConfigResourceLength(),
                                                          userCache.getNowLvMaxExp()), gatewayAttachment);
    }

    @PacketReceiver
    public void atGetPlayerInfoRequest(Session session, GetPlayerInfoRequest request, GatewayAttachment gatewayAttachment) {
        var token = request.getToken();
        if (StringUtils.isBlank(token)) {
            logger.info("[sid:{}]token传递空值，token:[{}]", session.getSid(), token);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_protocol_param.toString()), gatewayAttachment);
            return;
        }
        //解析token
        var triple = TokenUtils.get(token);
        var uid = triple.getLeft();
        var sid = session.getSid();
        logger.info("[{}][{}]玩家信息[token:{}]", uid, sid, token);
        var player = userLoginService.LoadPlayerUserEntity(uid);
        if (player == null) {
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_user_orm_no_uid.toString()));
            logger.info("[{}][{}]玩家信息不存在与数据库有中，token:[{}]", uid, sid, token);
            return;
        }
        // 设置session
        player.sid = sid;
        player.session = session;
        session.setUid(uid);
        logger.info("返回玩家 [uid:{}]  基础信息", player.getId());
        NetContext.getRouter().send(session, LoginResponse.valueOf(token,
                                                                   player.getName(),
                                                                   player.id(),
                                                                   player.getGoldNum(),
                                                                   player.getPremiumDiamondNum(),
                                                                   player.getDiamondNum(),
                                                                   player.getPlayerLv(),
                                                                   player.getNowExp(),
                                                                   userLoginService.ConfigResourceLength(),
                                                                   player.getNowLvMaxExp()),
                                    gatewayAttachment);
    }

    @PacketReceiver
    public void atLogoutRequest(Session session, LogoutRequest request, GatewayAttachment gatewayAttachment) {
        //拿到uid
        var uid = session.getUid();
        var sid = session.getSid();
        logger.info("[{}][{}]玩家退出游戏", uid, sid);
        var player = userLoginService.LoadPlayerUserEntity(uid);
        player.sid = 0;
        player.session = null;
        player.setEndLoginOutTime(TimeUtils.now());
        logger.info("[退出游戏] 结束更新玩家退出游戏时间");
        userLoginService.UpdatePlayerUserEntity(player);
    }

    @PacketReceiver
    public void atPing(Session session, Ping request, GatewayAttachment gatewayAttachment) {
        NetContext.getRouter().send(session, Pong.valueOf(TimeUtils.now()), gatewayAttachment);
    }
}
