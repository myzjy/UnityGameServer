package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 使用道具
 *
 * @author zjy
 * @version 1.0
 * @since 2023/7/27 19 29
 */
@Protocol(id = 1033)
public class UseTheBagItemEffectRequest implements IPacket, IGatewayLoadBalancer {
 
    /**
     * 道具 id
     */
    private int itemId;

    /**
     * 使用数量
     */
    private int userTheNum;
    /**
     * 道具类型
     */
    private int itemType;
    /**
     * 使用 玩家id
     */
    private long userID;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return itemId;
    }
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserTheNum() {
        return userTheNum;
    }

    public void setUserTheNum(int userTheNum) {
        this.userTheNum = userTheNum;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}
