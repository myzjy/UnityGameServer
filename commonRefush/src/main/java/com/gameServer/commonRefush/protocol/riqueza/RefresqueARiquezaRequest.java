package com.gameServer.commonRefush.protocol.riqueza;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 23 58
 */
public class RefresqueARiquezaRequest implements IPacket, IGatewayLoadBalancer {
    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
}
