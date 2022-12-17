package com.gameServer.singleServer;

import com.gameServer.commonRefush.constant.TankDeployEnum;
import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.net.core.json.JsonWebsocketServer;
import com.zfoo.util.ThreadUtils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/8/14 22:44
 */
@Component
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
