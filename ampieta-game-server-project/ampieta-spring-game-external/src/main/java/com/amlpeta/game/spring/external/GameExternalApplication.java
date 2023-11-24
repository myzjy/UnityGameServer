package com.amlpeta.game.spring.external;

import com.iohao.game.external.core.ExternalServer;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/11/24 15 29
 */
public class GameExternalApplication {
    public static void main(String[] args) {
        // 对外开放的端口
        int externalPort = 10100;

        // 构建游戏对外服
        ExternalServer externalServer = new GameExternal().createExternalServer(externalPort);

        // 启动游戏对外服
        externalServer.startup();
    }
}
