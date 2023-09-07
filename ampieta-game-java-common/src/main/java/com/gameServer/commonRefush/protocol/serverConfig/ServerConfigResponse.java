package com.gameServer.commonRefush.protocol.serverConfig;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
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
    public List<ItemBaseData> bagItemEntityList;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public static ServerConfigResponse ValueOf(List<ItemBaseData> bagItemEntityList) {
        ServerConfigResponse value = new ServerConfigResponse();
        //重新指向 保证修改之后 不会被引用指针修改
        value.bagItemEntityList = new ArrayList<>();
        value.bagItemEntityList.addAll(bagItemEntityList);
//        value.bagItemEntityList = bagItemEntityList;
        return value;
    }

}
