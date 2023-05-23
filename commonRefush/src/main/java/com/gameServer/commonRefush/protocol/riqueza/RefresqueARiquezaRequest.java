package com.gameServer.commonRefush.protocol.riqueza;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 刷新主界面的金币 钻石 付费钻石
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 23 58
 */
@Protocol(id = 1027)
public class RefresqueARiquezaRequest implements IPacket, IGatewayLoadBalancer {
    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
    public static RefresqueARiquezaRequest ValueOf(){
        return new RefresqueARiquezaRequest();
    }


}
