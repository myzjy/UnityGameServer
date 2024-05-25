package com.gameServer.common.protocol.login;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/2/2 13 24
 */
@Protocol(id = 1015)
public class LoginByHttpTokenRequest {
    private String token;

    @Note("0是默认登录，1为断线重连")
    private int reason;
    @Note("配置版本号")
    private String confVersion;
    @Note("资源版本号")
    private String resourceVersion;
    @Note("客户端版本号")
    private String appVersion;
    @Note("ip")
    private String ip;

    public static LoginByHttpTokenRequest valueOf(String token) {
        var request = new LoginByHttpTokenRequest();
        request.token = token;
        return request;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getReason() {
        return reason;
    }

    public void setReason(int reason) {
        this.reason = reason;
    }

    public String getConfVersion() {
        return confVersion;
    }

    public void setConfVersion(String confVersion) {
        this.confVersion = confVersion;
    }

    public String getResourceVersion() {
        return resourceVersion;
    }

    public void setResourceVersion(String resourceVersion) {
        this.resourceVersion = resourceVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
