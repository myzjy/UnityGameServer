package com.ampIeta.game.spring.broker;

import com.iohao.game.bolt.broker.server.BrokerServer;
import com.iohao.game.bolt.broker.server.BrokerServerBuilder;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/24 17 44
 */
public class GameBrokerBoot {
    public BrokerServer createBrokerServer() {
        // broker （游戏网关） 构建器
        BrokerServerBuilder brokerServerBuilder = BrokerServer.newBuilder();
        return brokerServerBuilder.build();
    }
}
