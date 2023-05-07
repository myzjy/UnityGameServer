package com.gameServer.commonRefush.protocol.riqueza;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;

/**
 * 返回自己拥有金币 钻石 付费钻石
 * 刷新 也可以作为返回
 *
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 23 59
 */
public class RefresqueARiquezaResponse implements IPacket, IGatewayLoadBalancer {
    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
}
