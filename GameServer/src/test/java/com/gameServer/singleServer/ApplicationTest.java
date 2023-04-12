package com.gameServer.singleServer;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.gameServer.commonRefush.entity.AccessGameTimeEntity;
import com.gameServer.commonRefush.event.bag.StartLoginBagEvent;
import com.gameServer.commonRefush.event.create.CreateOrmAccesTimeEvent;
import com.gameServer.commonRefush.protocol.login.LogRequest;
import com.gameServer.commonRefush.resource.AccesGameTimeResource;
import com.zfoo.event.manager.EventBus;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.net.NetContext;
import com.zfoo.net.config.model.NetConfig;
import com.zfoo.net.core.json.JsonWebsocketClient;
import com.zfoo.net.core.json.JsonWebsocketServer;
import com.zfoo.orm.OrmContext;
import com.zfoo.storage.model.anno.ResInjection;
import com.zfoo.storage.model.vo.Storage;
import com.zfoo.util.ThreadUtils;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/8/14 22:44
 */
//@Ignore
@Component
public class ApplicationTest {

    static {
        TankDeployEnum.InitDefaultEnv();
    }

    //最后启动
    @Test
    public void StartTcpApplication3() {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
        var webSocketClientProtocolConfig = WebSocketClientProtocolConfig.newBuilder()
                .webSocketUri(String.format("ws://%s/websocket", Application.GATEWAY_HOST_AND_PORT.toHostAndPortStr()))
                .build();
        //webSocket服务器
        var websocketServer = new JsonWebsocketClient(Application.GATEWAY_HOST_AND_PORT, webSocketClientProtocolConfig);
        var session = websocketServer.start();
        for (int i = 0; i < 1000; i++) {
            NetContext.getRouter().send(session, new LogRequest());
            ThreadUtils.sleep(2000);
        }
        ThreadUtils.sleep(Long.MAX_VALUE);
    }

    //第二个启动
    @Test
    public void StartTcpApplication1() {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
        var webSocketClientProtocolConfig = WebSocketClientProtocolConfig.newBuilder()
                .webSocketUri(String.format("ws://%s/websocket", Application.GATEWAY_HOST_AND_PORT.toHostAndPortStr()))
                .build();
        //webSocket服务器
        var websocketServer = new JsonWebsocketClient(Application.GATEWAY_HOST_AND_PORT, webSocketClientProtocolConfig);
        websocketServer.start();
        ThreadUtils.sleep(Long.MAX_VALUE);
    }

    //先启动
    @Test
    public void StartTcpApplication() {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
        //webSocket服务器
        var websocketServer = new JsonWebsocketServer(Application.GATEWAY_HOST_AND_PORT);
        websocketServer.start();
        ThreadUtils.sleep(Long.MAX_VALUE);
    }

    @Test
    public void StartTcpApplication2() {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
        EventBus.post(new CreateOrmAccesTimeEvent());

    }
}
