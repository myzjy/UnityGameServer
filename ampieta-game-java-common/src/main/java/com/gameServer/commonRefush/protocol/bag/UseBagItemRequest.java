package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 使用背包道具 这个协议中不包括 使用装备
 *
 * @author zjy
 * @version 1.0
 * @since 2023/2/15 23:28
 */
@Protocol(id = 1011)
public class UseBagItemRequest implements IPacket {
    public static UseBagItemRequest ValueOf() {
        return new UseBagItemRequest();
    }

}
