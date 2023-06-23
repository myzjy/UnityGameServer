package com.gameServer.cache;

import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/10 22:56
 */
public class Application {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        System.out.println("context.publishEvent(new AppStartEvent(context))");
        context.publishEvent(new AppStartEvent(context));
        System.out.println("结束流程");
    }
}
