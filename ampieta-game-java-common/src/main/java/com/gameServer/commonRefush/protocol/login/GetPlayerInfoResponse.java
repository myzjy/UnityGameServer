package com.gameServer.commonRefush.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 获取玩家数据
 *
 * @author zjy
 * @version 1.0
 * @since 2022/12/30 23:39
 */
@Protocol(id = 1012)
public class GetPlayerInfoResponse implements IPacket, IGatewayLoadBalancer {
    @Override
    public Object loadBalancerConsistentHashObject() {
//        AesUtils
        return null;
    }

    @Override
    public boolean verificationUid() {
        return IPacket.super.verificationUid();
    }
}