package com.gameServer.httpsServer.response.sdkToken;

import org.springframework.stereotype.Component;

/**
 * 检验的sdk token 返回 检验过后 的 token
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/3 11 57
 */
@Component
public class RefreshSdkTokenServerResponse {
    /**
     * 检验过后 的 token
     */
    private String sdkString;

    public static RefreshSdkTokenServerResponse ValueOf(String tokenString) {
        var data = new RefreshSdkTokenServerResponse();
        data.setSdkString(tokenString);
        return data;
    }

    public String getSdkString() {
        return sdkString;
    }

    public void setSdkString(String sdkString) {
        this.sdkString = sdkString;
    }
}
