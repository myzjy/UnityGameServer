package com.gameServer.commonRefush.protocol.sdk.login;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

/**
 * sdk登录
 *
 * @author zjy
 * @version 1.0
 * @since 2023/5/7 21 03
 */
@Protocol(id = 5000)
public class SDKLoginRequest implements IPacket, IGatewayLoadBalancer {
    /**
     * @apiNote account 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    @Override
    public Object loadBalancerConsistentHashObject() {
        return account;
    }

    /**
     * @param account  账号
     * @param password 密码
     * @apiNote 创建类
     */
    public static SDKLoginRequest valueOf(String account, String password) {
        var Request = new SDKLoginRequest();
        Request.account = account;
        Request.password = password;
        return Request;
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
