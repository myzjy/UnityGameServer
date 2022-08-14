package com.unitygameServer.serverGame.singleServer;

import com.unitygameServer.serverGame.commonRefush.constant.TankDeployEnum;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.net.core.websocket.WebsocketServer;
import com.zfoo.util.ThreadUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * @author Administrator
 * @version 1.0
 * @since 2022/8/14 22:44
 */
public class ApplicationTest {

    static {
        TankDeployEnum.InitDefaultEnv();
    }

    @Test
    public void StartWebSocketApplication(){
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));

        //webSocket服务器
        var websocketServer = new WebsocketServer(Application.GATEWAY_HOST_AND_PORT);
        websocketServer.start();
        ThreadUtils.sleep(Long.MAX_VALUE);

    }
}
