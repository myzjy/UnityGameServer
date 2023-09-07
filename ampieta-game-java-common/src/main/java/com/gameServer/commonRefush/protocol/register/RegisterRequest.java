package com.gameServer.commonRefush.protocol.register;

import com.zfoo.net.core.gateway.IGatewayLoadBalancer;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2022/11/3 23:34
 */
public class RegisterRequest implements IPacket, IGatewayLoadBalancer {
    public static final transient short PROTOCOL_ID = 1005;

    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }

    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String affirmPassword;

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

    public String getAffirmPassword() {
        return affirmPassword;
    }

    public void setAffirmPassword(String affirmPassword) {
        this.affirmPassword = affirmPassword;
    }

    /**
     * 创建注册相关数据
     *
     * @param account        需要注册的账号
     * @param password       需要注册账号的密码
     * @param affirmPassword 需要注册账号的密码二次确认
     */
    public static RegisterRequest valueOf(String account, String password, String affirmPassword) {
        var packet = new RegisterRequest();
        packet.account = account;
        packet.password = password;
        packet.affirmPassword = affirmPassword;
        return packet;
    }

    @Override
    public Object loadBalancerConsistentHashObject() {
        return account;
    }
}
