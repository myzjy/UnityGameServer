package com.ampIeta.game.spring.broker;

import com.iohao.game.bolt.broker.server.BrokerServer;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/24 17 45
 */
public class GameBrokerApplication {
    public static void main(String[] args) {

        // broker （游戏网关）
        BrokerServer brokerServer = new GameBrokerBoot().createBrokerServer();

        // 启动游戏网关
        brokerServer.startup();
    }
}
