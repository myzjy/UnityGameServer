package com.gameServer.commonRefush.protocol.login;

import com.gameServer.commonRefush.util.TokenUtils;
import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * 登录采用token的时候
 *
 * @author zjy
 * @version 1.0
 * @since 2022/9/25 10:59
 */
public class GetPlayerInfoRequest implements IPacket, IGatewayLoadBalancer {
    public static final transient short PROTOCOL_ID = 1004;

    public static short getProtocolId() {
        return PROTOCOL_ID;
    }

    public static GetPlayerInfoRequest valueOf(String token) {
        var packet = new GetPlayerInfoRequest();
        packet.token = token;
        return packet;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        var triple = TokenUtils.get(token);
        return triple.getLeft();
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

}