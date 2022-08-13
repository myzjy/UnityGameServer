package com.unitygameServer.serverGame.singleServer;

import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zjy
 * @version 1.0
 * @since 2022-8-11
 */
public class Application {
    /*
     * TCP
     * */
    public static final int TCP_SERVER_PORT = 5000;
    //    public static final int WEBSOCKET_SERVER_PORT=5000;
    public static final HostAndPort GATEWAY_HOST_AND_PORT = HostAndPort.valueOf(NetUtils.getLocalhostStr(), TCP_SERVER_PORT);

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));

    }
}
