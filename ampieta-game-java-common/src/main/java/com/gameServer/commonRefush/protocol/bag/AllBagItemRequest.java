package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.protocol.anno.Protocol;

/**
 * 获取背包的所有物品
 *
 * @author Administrator
 * @version 1.0
 * @since 2022/12/5 19:55
 */
@Protocol(id = 1007)
public class AllBagItemRequest {
    public static final transient short PROTOCOL_ID = 1007;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public static AllBagItemRequest ValueOf() {
        return new AllBagItemRequest();
    }
}
