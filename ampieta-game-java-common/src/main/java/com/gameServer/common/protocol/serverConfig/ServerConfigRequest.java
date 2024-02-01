package com.gameServer.common.protocol.serverConfig;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since
 */
@Protocol(id = 1009)
public class ServerConfigRequest implements IPacket, IGatewayLoadBalancer {
    public static final transient short PROTOCOL_ID = 1009;
    private String panel;

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return panel;
    }
}
