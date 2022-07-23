package com.unitygameServer.serverGame.commonRefush.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/17 11:32 PM
 */
public class LoginResponse implements IPacket {
    public static final transient short PROTOCOL_ID = 1001;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    /**
     * 拿到token之后返回出去
     * */
    public static LoginResponse valueOf(String token) {
        var paket = new LoginResponse();
        paket.token=token;
        return paket;
    }


    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
