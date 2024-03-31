package com.gameServer.common.protocol.character;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取角色 所有角色 服务器数据
 *
 * @author zjy
 * @version 1.0
 * @since 2024/3/31 22 43
 */
@Protocol(id = 1045)
public class AcquireCharacterRequest implements IPacket {
    public static AcquireCharacterRequest valueOf() {
        return new AcquireCharacterRequest();
    }
}
