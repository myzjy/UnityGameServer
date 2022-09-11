package com.unitygameServer.serverGame.singleServer.client;

import com.unitygameServer.serverGame.commonRefush.constant.TankDeployEnum;
import com.unitygameServer.serverGame.singleServer.Application;
import com.zfoo.net.NetContext;
import com.zfoo.net.core.tcp.TcpClient;
import com.zfoo.scheduler.util.TimeUtils;
import com.zfoo.util.ThreadUtils;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/10 0:41
 */
public class MyMMoClientTest {
    static {
        TankDeployEnum.InitDefaultEnv();
    }
    @Test
    public void tankClient() {
        var context = new ClassPathXmlApplicationContext("my-tank-application.xml");

        var myTankClient = new TcpClient(HostAndPort.valueOf(NetUtils.LOCAL_LOOPBACK_IP, Application.WEBSOCKET_SERVER_PORT));
        var myTankSession = myTankClient.start();


    }
}
