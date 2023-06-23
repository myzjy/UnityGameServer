package com.gameServer.gateway.controller;

import com.gameServer.commonRefush.protocol.login.LogoutRequest;
import com.zfoo.event.model.anno.EventReceiver;
import com.zfoo.net.NetContext;
import com.zfoo.net.core.gateway.model.GatewaySessionInactiveEvent;
import com.zfoo.net.router.attachment.GatewayAttachment;
import com.zfoo.protocol.ProtocolManager;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/4/19 17 44
 */
@Component
public class GatewayController {
    @EventReceiver
    public void onGatewaySessionInactiveEvent(GatewaySessionInactiveEvent event) {
        var sid = event.getSid();
        var uid = event.getUid();

        if (uid <= 0) {
            return;
        }

        var packet = LogoutRequest.valueOf();

        var loadBalancer = NetContext.getConsumer().loadBalancer(ProtocolManager.moduleByProtocolId(packet.protocolId()));
        var consumerSession = loadBalancer.loadBalancer(packet, uid);

        // 包的附加包，通过网关转发到home的包会丢失sid和uid，通过这个GatewayAttachment附带到IPacket后面，home就知道哪个玩家发的包了
        // 玩家登出
        var gatewayAttachment = new GatewayAttachment(sid, uid);
        gatewayAttachment.setClient(true);
        NetContext.getRouter().send(consumerSession, packet, gatewayAttachment);
    }

}
