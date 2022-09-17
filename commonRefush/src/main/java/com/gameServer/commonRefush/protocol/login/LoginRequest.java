package com.gameServer.commonRefush.protocol.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;


/**
 * @author zjy
 * @version 0.1
 * @since 2022/6/18 12:25
 */
public class LoginRequest implements IPacket, IGatewayLoadBalancer {

    public static final transient short PROTOCOL_ID = 1000;
    /**
     * @apiNote   account 账号
     * */
    private String account;
    /**
     * 密码
     * */
    private String password;

    /**
     * @apiNote 创建类
     * @param account 账号
     * @param password 密码
     * */

    public static LoginRequest valueOf(String account,String password) {
        var Request = new LoginRequest();
        Request.account=account;
        Request.password =password;
        return Request;
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
