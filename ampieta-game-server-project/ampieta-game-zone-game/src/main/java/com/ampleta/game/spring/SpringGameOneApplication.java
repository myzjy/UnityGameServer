package com.ampleta.game.spring;

import com.amlpeta.game.spring.external.GameExternal;
import com.ampIeta.game.spring.broker.GameBrokerBoot;
import com.iohao.game.action.skeleton.ext.spring.ActionFactoryBeanForSpring;
import com.iohao.game.bolt.broker.client.AbstractBrokerClientStartup;
import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.external.core.ExternalServer;
import com.iohao.game.external.core.config.ExternalJoinEnum;
import com.iohao.game.external.core.netty.simple.NettyRunOne;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/24 14 24
 */
@Slf4j
@SpringBootApplication
public class SpringGameOneApplication {
    public static void main(String[] args) {
        List<AbstractBrokerClientStartup> logicList=List.of();
        // 启动 spring boot
        SpringApplication.run(SpringGameOneApplication.class, args);
        // 对外开放的端口
        int externalPort = 10100;
        // 游戏对外服
        ExternalServer externalServerWebSocket = new GameExternal()
                .createExternalServer(externalPort);
        // externalPort + 1
        int tcpPort = ExternalJoinEnum.TCP.cocPort(externalPort);
        ExternalServer externalServerTcp = new GameExternal()
                .createExternalServer(tcpPort, ExternalJoinEnum.TCP);
        // broker （游戏网关）
        BrokerServer brokerServer = new GameBrokerBoot().createBrokerServer();

        // 多服单进程的方式部署（类似单体应用）
        new NettyRunOne()
                // broker （游戏网关）
                .setBrokerServer(brokerServer)
                // 游戏对外服
                .setExternalServerList(List.of(externalServerWebSocket, externalServerTcp))
                // 游戏逻辑服列表
                .setLogicServerList(logicList)
                // 启动 游戏对外服、游戏网关、游戏逻辑服
                .startup();
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        // 将业务框架交给 spring 管理
        return ActionFactoryBeanForSpring.me();
    }

    //private static SameRoomLogicClient createRoomLogicClient(int id) {
    //    // BrokerClient 构建器，房间逻辑服的信息
    //    BrokerClientBuilder brokerClientBuilder = BrokerClient.newBuilder()
    //                                                          // 逻辑服的唯一 id
    //                                                          .id(String.valueOf(id))
    //                                                          // 逻辑服名字
    //                                                          .appName("spring 房间的游戏逻辑服-" + id)
    //                                                          // 同类型标签
    //                                                          .tag("roomLogic");
    //
    //    // 创建房间的逻辑服
    //    SameRoomLogicClient sameRoomLogicClient = new SameRoomLogicClient();
    //    // 如果字段赋值了，就不会使用 BrokerClientStartup.createBrokerClientBuilder() 接口的值
    //    sameRoomLogicClient.setBrokerClientBuilder(brokerClientBuilder);
    //    return sameRoomLogicClient;
    //}
}
