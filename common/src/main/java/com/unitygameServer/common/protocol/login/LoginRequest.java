package com.unitygameServer.common.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;

/**
 * @author zjy
 * @version 0.1
 * @since 11:09 下午
 */
public class LoginRequest implements IPacket, IGatewayLoadBalancer {
    public static final transient short PROTOCOL_ID = 1000;

    private String account;
    private String password;
    public static LoginRequest valueof(String account,String password){
        var request=new LoginRequest();
        request.account=account;
        request.password=password;
        return  request;
    }


    @Override
    public Object loadBalancerConsistentHashObject() {
        return account;
    }

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
