package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * 使用体力相关道具 或者 使用
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/14 12 00
 */
@Protocol(id = 1025)
public class PhysicalPowerUserPropsRequest implements IPacket, IGatewayLoadBalancer {

    /**
     * 使用体力 会被扣除
     */
    private int usePropNum;

    public static PhysicalPowerUserPropsRequest ValueOf() {
        var data = new PhysicalPowerUserPropsRequest();
        return data;
    }

    public int getUsePropNum() {
        return usePropNum;
    }

    public void setUsePropNum(int usePropNum) {
        this.usePropNum = usePropNum;
    }


    @Override
    public Object loadBalancerConsistentHashObject() {
        return usePropNum;
    }
}
