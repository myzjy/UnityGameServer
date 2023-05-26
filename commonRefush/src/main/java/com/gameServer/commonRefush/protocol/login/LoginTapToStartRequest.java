package com.gameServer.commonRefush.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

import javax.swing.*;

/**
 * 当玩家点击登录游戏 请求，可以作为拦截
 *
 * @author zjy
 * @version 1.0
 * @since 2023/2/11 1:09
 */
@Protocol(id = 1013)
public class LoginTapToStartRequest implements IPacket, IGatewayLoadBalancer {


    private String clientName;
    public static LoginTapToStartRequest valueOf() {
        var Request = new LoginTapToStartRequest();

        return Request;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return null;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
