package com.gameServer.common.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * 当玩家点击登录游戏 请求，可以作为拦截
 *
 * @author zjy
 * @version 1.0
 * @since 2023/2/11 1:09
 */
@Protocol(id = 1013)
public class LoginTapToStartRequest implements IPacket, IGatewayLoadBalancer {


    @Note("1级的攻击属性")
    private String clientName;
    public static LoginTapToStartRequest valueOf() {
        return new LoginTapToStartRequest();
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return clientName;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
