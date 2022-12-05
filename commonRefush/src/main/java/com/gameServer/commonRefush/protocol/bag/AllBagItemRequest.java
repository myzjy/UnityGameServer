package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.protocol.IPacket;

/**
 * 获取背包的所有物品
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 19:55
 */
public class AllBagItemRequest implements IPacket {
    public static final transient short PROTOCOL_ID = 1007;

    public static AllBagItemRequest ValueOf() {
        return new AllBagItemRequest();
    }
}
