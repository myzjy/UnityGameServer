package com.gameServer.common.protocol.physicalPower;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 获取体力 在登录 进入主界面的时候
 *
 * @author zjy
 * @version 1.0
 * @since 2023/4/12 16 25
 */
@Protocol(id = 1023)
public class PhysicalPowerRequest implements IPacket, IGatewayLoadBalancer {

    /**
     * 玩家uid传过来，可能会用到
     */
    @Note("玩家uid传过来，可能会用到")
    private long uid;

    public static PhysicalPowerRequest ValueOf() {
        var data = new PhysicalPowerRequest();
        return data;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return uid;
    }
}
