package com.gameServer.common.protocol.login;

import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/17 11:32 PM
 */
@Protocol(id = 1001)
public class LoginResponse implements IPacket {
    @Note("玩家一些数据")
    private LoginUserServerInfoData loginUserServerInfoData;
    @Note("玩家数据库加密token")
    private String token;
    @Note("玩家id")
    private long uid;

    /**
     * 拿到token之后返回出去
     */
    public static LoginResponse valueOf() {
        return new LoginResponse();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public LoginUserServerInfoData getLoginUserServerInfoData() {
        return loginUserServerInfoData;
    }

    public void setLoginUserServerInfoData(LoginUserServerInfoData loginUserServerInfoData) {
        this.loginUserServerInfoData = loginUserServerInfoData;
    }
}
