package com.gameServer.common.protocol.weapon;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/1/13 18 16
 */
@Protocol(id = 1039)
public class WeaponPlayerUserDataRequest implements IPacket, IGatewayLoadBalancer {
    /**
     * 需要查找的玩家id
     */
    private long findUserId;
    /**
     * 需要成查找的某件装备 为0 代表 不需要查找特定装备
     * 部位负数或0的时候 就需要查找特定装备
     */
    private long findWeaponId;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return findUserId;
    }

    public static WeaponPlayerUserDataRequest valueOf() {
        return new WeaponPlayerUserDataRequest();
    }
}
