package com.gameServer.commonRefush.protocol.serverConfig;

import com.zfoo.protocol.IPacket;

/**
 * 获取基础配置表
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/18 1:48
 */
public class ServerConfigResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1010;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}