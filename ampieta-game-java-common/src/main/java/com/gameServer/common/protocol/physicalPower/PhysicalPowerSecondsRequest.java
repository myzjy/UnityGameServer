package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 体力恢复每秒交互
 *
 * @author zjy
 * @version 1.0
 * @since 2023/6/27 18 55
 */
@Protocol(id = 1029)
public class PhysicalPowerSecondsRequest implements IPacket , IGatewayLoadBalancer {
    /**
     * 当前时间
     */
    @Note("当前时间")
    private long nowTime;

    public static PhysicalPowerSecondsRequest ValueOf() {
        var data = new PhysicalPowerSecondsRequest();
        return data;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return nowTime;
    }
}
