package com.gameServer.gameBoot;

import com.zfoo.event.model.event.AppStartEvent;
import com.zfoo.util.net.HostAndPort;
import com.zfoo.util.net.NetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/9/18 1:07
 */
@SpringBootApplication(exclude = {
        // 排除JDBC自动配置
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcRepositoriesAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class,
        JndiDataSourceAutoConfiguration.class,

        // 排除MongoDB自动配置
        MongoDataAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class,
        MongoAutoConfiguration.class,
        MongoReactiveAutoConfiguration.class,
        MongoReactiveDataAutoConfiguration.class,
        MongoReactiveRepositoriesAutoConfiguration.class,


        // 排除Redis自动配置
        RedisAutoConfiguration.class,
        RedisReactiveAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class})
//@EnableEurekaClient
@RestController
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static final HostAndPort GATEWAY_HOST_AND_PORT = HostAndPort.valueOf(NetUtils.getLocalhostStr(), 5000);
    
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes().route("path_route", r -> r.path("/get").uri("http://" + GATEWAY_HOST_AND_PORT.toHostAndPortStr())).build();
    }

    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);
//        context.registerShutdownHook();
//        context.publishEvent(new AppStartEvent(context));
        logger.info("Start Web Application!");
    }
}
