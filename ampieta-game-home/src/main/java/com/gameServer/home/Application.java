package com.gameServer.home;

import com.zfoo.event.model.AppStartEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/18 19 42
 */
public class Application {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        context.registerShutdownHook();
        context.publishEvent(new AppStartEvent(context));
    }
}
