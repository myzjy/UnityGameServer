package com.unitygameServer.serverGame.singleServer.LoginServer;

import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.net.core.websocket.WebsocketServer;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;

public class LoginServer {

    public static final int WEBSOCKET_SERVER_PORT = 8080;


    public static void main(String[] args) {
        var springApp = SpringApplication.run(LoginServer.class, args);
        springApp.registerShutdownHook();
        springApp.publishEvent(new AppStartEvent(springApp));
        // 启动websocket
        var websocket = new WebsocketServer(HostAndPort.valueOf(NetUtils.getLocalhostStr(), WEBSOCKET_SERVER_PORT));
        websocket.start();
    }
}
