package com.gameServer.singleServer.login.controller;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.entity.PhysicalPowerEntity;
import com.gameServer.commonRefush.entity.PlayerUserEntity;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAnswer;
import com.gameServer.commonRefush.protocol.cache.create.CreatePhysicalPowerAsk;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartRequest;
import com.gameServer.commonRefush.protocol.login.LoginTapToStartResponse;
import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerRequest;
import com.gameServer.commonRefush.protocol.physicalPower.PhysicalPowerResponse;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.packet.common.Error;
import com.zfoo.net.router.receiver.PacketReceiver;
import com.zfoo.net.session.Session;
import com.zfoo.orm.OrmContext;
import com.zfoo.scheduler.util.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Objects;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/2/17 23:39
 */
@Component
public class LoginTapStartController {
    //    private AccessGameTimeEntity timeEntity;
    private static final Logger logger = LoggerFactory.getLogger(LoginTapStartController.class);
    @Value("${spring.profiles.active}")
    private TankDeployEnum deployEnum;

    @PacketReceiver
    public void atLoginTapToStartRequest(Session session, LoginTapToStartRequest request) throws ParseException {
        logger.info("=============================================");
        logger.info("[当前服务器调用时间{}] [调用协议：{}]", TimeUtils.simpleDateString(), request.protocolId());
        logger.info("=============================================");
        //读取到服务器
        var timeEntityList = OrmContext.getAccessor().load(1, AccessGameTimeEntity.class);
        var dateTime = TimeUtils.timeToString(Objects.requireNonNull(timeEntityList).getTime());
        var time = TimeUtils.dayStringToDate(dateTime);
        logger.info(dateTime);
        var nowTimeEntity = timeEntityList.getTime();
        if (TimeUtils.now() < nowTimeEntity) {
            logger.info("[服务器开启] 可以开始链接登录");
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("服务器正在开启阶段", true));
        } else {
            logger.info("[关闭服务器时间{}] ", dateTime);
            NetContext.getRouter().send(session, LoginTapToStartResponse.ValueOf("服务器已关闭", false));
        }
    }

    @PacketReceiver
    public void atPhysicalPowerRequest(Session session, PhysicalPowerRequest request) throws NullPointerException {
        logger.info("[uid:{}] 获取体力 atPhysicalPowerRequest", session.getUid());
        //获取到服务器 数据库存放
        var data = OrmContext.getAccessor().load(session.getUid(), PhysicalPowerEntity.class);
        if (data == null) {
            var serverSession = NetContext.getSessionManager().getServerSession(1);
            var user = OrmContext.getAccessor().load(session.getUid(), PlayerUserEntity.class);
            if (user == null) {
                NetContext.getRouter().send(session, Error.valueOf("数据库错误，传递错误uid"));
                return;
            }
            //这种情况一般不会有的，如果有那就rpc通信去创建
            NetContext.getRouter().asyncAsk(serverSession,
                    CreatePhysicalPowerAsk.ValueOf(user.getPlayerLv(), user.getId()),
                    CreatePhysicalPowerAnswer.class, null).whenComplete(res -> {
                //体力重新创建出来了，返回出去
                var createData = OrmContext.getAccessor().load(session.getUid(), PhysicalPowerEntity.class);
                if (createData == null) {
                    NetContext.getRouter().send(session, Error.valueOf("数据库错误，创建数据错误，请联系客服"));
                    return;
                }
                //有了数据传递过去
                NetContext.getRouter().send(session, PhysicalPowerResponse.ValueOf(createData.getNowPhysicalPowerNum(), createData.getResidueTime(),
                        createData.getMaximumStrength(), createData.getMaximusResidueEndTime(), createData.getResidueNowTime()));
            });
        } else {
            //有了数据传递过去
            NetContext.getRouter().send(session, PhysicalPowerResponse.ValueOf(data.getNowPhysicalPowerNum(), data.getResidueTime(),
                    data.getMaximumStrength(), data.getMaximusResidueEndTime(), data.getResidueNowTime()));
        }
    }


}
