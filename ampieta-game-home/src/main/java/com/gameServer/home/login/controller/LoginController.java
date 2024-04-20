package com.gameServer.home.login.controller;

import com.gameServer.common.cache.character.LoginCreateCharacterAnswer;
import com.gameServer.common.cache.character.LoginCreateCharacterAsk;
import com.gameServer.common.cache.weapon.CreateWeaponDefaultAnswer;
import com.gameServer.common.cache.weapon.CreateWeaponDefaultAsk;
import com.gameServer.common.constant.I18nEnum;
import com.gameServer.common.constant.TankDeployEnum;
import com.gameServer.common.entity.AccountEntity;
import com.gameServer.common.entity.CharacterPlayerUserEntity;
import com.gameServer.common.entity.PlayerUserEntity;
import com.gameServer.common.entity.character.GameMainTeamCharacterListEntity;
import com.gameServer.common.entity.composite.CharacterUserCompositeDataID;
import com.gameServer.common.entity.composite.CharacterUserWeaponCompositeDataID;
import com.gameServer.common.ormEntity.CharacterConfigEntity;
import com.gameServer.common.protocol.login.*;
import com.gameServer.common.protocol.playerUser.PlayerSceneInfoData;
import com.gameServer.common.protocol.playerUser.UserMsgInfoData;
import com.gameServer.common.util.TokenUtils;
import com.gameServer.home.PhysicalPower.service.IPhysicalPowerService;
import com.gameServer.home.character.service.ICharacterService;
import com.gameServer.home.gameMain.service.IGameMainService;
import com.gameServer.home.user.service.IUserLoginService;
import com.zfoo.net.NetContext;
import com.zfoo.net.anno.PacketReceiver;
import com.zfoo.net.core.gateway.model.AuthUidToGatewayCheck;
import com.zfoo.net.core.gateway.model.AuthUidToGatewayConfirm;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.packet.common.Ping;
import com.zfoo.net.packet.common.Pong;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.protocol.util.JsonUtils;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
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
    @Autowired
    private ICharacterService characterService;
    @Autowired
    private IGameMainService gameMainService;
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
        //var sid = session.getSid();
        var accountUser = OrmContext.getQuery(AccountEntity.class).eq("account", account).queryFirst();
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
            logger.error("发送过来 [uid:{}] 数据库不存在相关人物，请注意！！！！！！！ 登录失败", uid);
            //必须保证账号存在
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()), gatewayAttachment);
            return;
        }
        //获取的玩家 uid小于0
        if (userCache.getId() <= 0) {
            logger.error("[玩家当前uid:{}][sid：{}],错误值，请检查", userCache.getId(), session.getSid());
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_account_not_exit.toString()));
            return;
        }
        var data = userLoginService.GetConfigResourceData(userCache.getPlayerLv());
        session.setUid(userCache.getId());
        var powerData = physicalPowerService.FindOnePhysicalPower(session.getUid());
        if (powerData == null) {
            logger.error("[uid:{}] 刷新 玩家自己体力 缓存数据库 出现错误,error:{}", uid, I18nEnum.error_login_process_not.toString());
            logger.error("[uid:{}] 玩家登录失败", uid);
            NetContext.getRouter().send(session, Error.valueOf(I18nEnum.error_login_process_not.toString()), gatewayAttachment);
            return;
        }
        physicalPowerService.RefreshLoginPhysicalPower(session.getUid());
        //无需赋值 体力值 刷新的时候 已经赋值
        userCache = userLoginService.LoadPlayerUserEntity(uid);
        //以防测试期间出现问题
        if (userCache.getToken() == null) {
            logger.info("[当前 uid:{}] 开始获取token", userCache.getId());
            //没有Token,获取token
            var token = TokenUtils.set(userCache.getId());
            logger.info("[当前 uid:{}][新token：{}]", userCache.getId(), token);
            userCache.setToken(token);
        }
        //防止token 过时
        var tokenTriple = TokenUtils.get(userCache.getToken());
        var salt = tokenTriple.getMiddle();
        var expirationTimeLong = tokenTriple.getRight();
        var nowLong = TimeUtils.now();
        logger.info("当前token：{},[当前uid：{}],[当前sid：{}][salt:{}]", tokenTriple, session.getUid(), session.getSid(), salt);
        logger.info("[expirationTimeLong:{}],[nowLog:{}][当前token是否过期：{}][expirationTimeLong:{}]", TimeUtils.timeToString(expirationTimeLong), TimeUtils.timeToString(nowLong), nowLong > expirationTimeLong, expirationTimeLong);
        if (nowLong > expirationTimeLong) {
            //代表过时的token
            var token = TokenUtils.set(userCache.getId());
            logger.info("[{}]重新设置Token:[{}]", userCache.getId(), token);
            userCache.setToken(token);
        }
        userCache.setNowLvMaxExp(data.getMaxExp());
        //覆盖登录时间
        userCache.setLastLoginTime(TimeUtils.now());
        logger.info("[{}][{}]创建最新玩家登录数据 更新数据库", userCache.getId(), sid);
        userCache.sid = sid;
        userCache.session = session;
        logger.info("[玩家{}] 更新 玩家数据缓存 赋值 session sid", uid);
        logger.info("[{}][{}]数据库刷新成功", userCache.getId(), sid);
        userLoginService.UpdatePlayerUserEntity(userCache);
        logger.info("UID:{}, 更新过后 PlayerUserEntity:{}", uid, JsonUtils.object2String(userCache));
        //不需要 创建 角色
        var list = OrmContext.getQuery(CharacterPlayerUserEntity.class).queryAll();
        if (list.isEmpty()) {
            //当前 角色 配置
            var config = OrmContext.getAccessor().load(10001, CharacterConfigEntity.class);
            if (config != null) {
                // 创建武器的 rpc
                var sk = CreateWeaponDefaultAsk.valueOf(config.getCharacterDefaultWeaponId(), config.getWeaponType(), 10001, session.getUid());
                var Data = NetContext.getConsumer().syncAsk(sk, CreateWeaponDefaultAnswer.class, null).packet();
                NetContext.getConsumer().syncAsk(LoginCreateCharacterAsk.valueOf(10001, Data.getWeaponIndex()), LoginCreateCharacterAnswer.class, null).packet();
                //返回数据
                var entity = OrmContext.getAccessor().load(session.getUid(), GameMainTeamCharacterListEntity.class);
                if (entity == null) {
                    entity = gameMainService.CreateInitGameMainTeamCharacterListEntity(10001, session.getUid());
                    logger.info("插入 当前玩家[uid:{}], GameMainTeamCharacterListEntity:{}", session.getUid(), JsonUtils.object2String(entity));
                    OrmContext.getAccessor().insert(entity);
                }
            }
        }
        var response = LoginResponse.valueOf();
        response.setUid(userCache.id());
        response.setToken(userCache.getToken());
        var userInfo = LoginUserServerInfoData.valueOf();
        var userMsgInfoData = UserMsgInfoData.valueOf();
        userMsgInfoData.setUserName(userCache.getName());
        userMsgInfoData.setExp(userCache.getNowExp());
        userMsgInfoData.setMaxExp(userCache.getNowLvMaxExp());
        userMsgInfoData.setLv(userCache.getPlayerLv());
        userMsgInfoData.setMaxLv(userLoginService.ConfigResourceLength());
        userMsgInfoData.setDiamondNum(userCache.getDiamondNum());
        userMsgInfoData.setGoldNum(userCache.getGoldNum());
        userMsgInfoData.setPremiumDiamondNum(userCache.getPremiumDiamondNum());
        var playerSceneInfoData = PlayerSceneInfoData.valueOf();

        userInfo.setPlayerSceneInfoData(playerSceneInfoData);
        userInfo.setUserMsgInfoData(userMsgInfoData);
        response.setLoginUserServerInfoData(userInfo);
        logger.info("LoginResponse:{}", JsonUtils.object2String(response));
        NetContext.getRouter().send(session, response
                , gatewayAttachment);
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
        NetContext.getRouter().send(session, LoginResponse.valueOf(),
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
}
