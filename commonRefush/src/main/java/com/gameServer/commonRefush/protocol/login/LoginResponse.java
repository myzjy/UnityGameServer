package com.gameServer.commonRefush.protocol.login;

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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private long uid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    /**
     * 拿到token之后返回出去
     * */
    public static LoginResponse valueOf(String token,String userName,long uid) {
        var paket = new LoginResponse();
        paket.token=token;
        //可能会为，因为没有到玩家取名步骤
        paket.userName=userName;
        paket.uid=uid;
        return paket;
    }


    @Override
    public short protocolId() {
        return PROTOCOL_ID;
    }
}
