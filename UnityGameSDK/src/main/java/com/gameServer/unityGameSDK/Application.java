package com.gameServer.unityGameSDK;

import com.zfoo.event.model.event.AppStartEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/5/6 13 16
 */
public class Application {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
    }
}
