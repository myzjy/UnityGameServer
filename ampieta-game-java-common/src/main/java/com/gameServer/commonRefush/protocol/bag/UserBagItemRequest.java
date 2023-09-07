package com.gameServer.commonRefush.protocol.bag;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 *  玩家使用道具
 * @author zjy
 * @version 1.0
 * @since 2023/7/27 19 28
 */
//@Protocol()
public class UserBagItemRequest implements IPacket , IGatewayLoadBalancer {
    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }

    @Override
    public short protocolId() {
        return IPacket.super.protocolId();
    }
}
