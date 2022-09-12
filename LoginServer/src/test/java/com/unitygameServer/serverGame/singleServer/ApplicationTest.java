package com.unitygameServer.serverGame.singleServer;

import com.unitygameServer.serverGame.commonRefush.constant.TankDeployEnum;
import com.unitygameSever.gamenet.core.jsonbuf.JsonBufTcpSever;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.net.core.json.JsonWebsocketServer;
import com.zfoo.net.core.tcp.TcpServer;
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
    public void StartTcpApplication(){
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));

        //webSocket服务器
        var websocketServer = new JsonWebsocketServer(Application.GATEWAY_HOST_AND_PORT);
        websocketServer.start();
        ThreadUtils.sleep(Long.MAX_VALUE);

    }
}
