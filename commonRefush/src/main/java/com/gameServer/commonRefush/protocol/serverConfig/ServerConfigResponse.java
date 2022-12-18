package com.gameServer.commonRefush.protocol.serverConfig;

import com.gameServer.commonRefush.entity.BagItemEntity;
import com.zfoo.protocol.IPacket;

import java.util.List;

/**
 * 获取基础配置表
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/18 1:48
 */
public class ServerConfigResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1010;
    /**
     * 背包基础类list
     */
    public List<BagItemEntity> bagItemEntityList;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public static ServerConfigResponse ValueOf(List<BagItemEntity> bagItemEntityList) {
        ServerConfigResponse value = new ServerConfigResponse();
        value.bagItemEntityList = bagItemEntityList;
        return value;
    }

}