package com.gameServer.common.protocol.weapon;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 18 16
 */
@Protocol(id = 1039)
public class WeaponPlayerUserDataRequest implements IPacket, IGatewayLoadBalancer {
    /**
     * 下标
     */
    private long id;
    /**
     * 玩家id
     */
    private long uid;
    /**
     * 武器id
     */
    private int weaponId;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
}
