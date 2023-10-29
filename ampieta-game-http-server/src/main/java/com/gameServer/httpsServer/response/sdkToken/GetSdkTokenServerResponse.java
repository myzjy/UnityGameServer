package com.gameServer.httpsServer.response.sdkToken;

import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/29 15 49
 */
@Component
public class GetSdkTokenServerResponse {
    private String sdkString;

    public static GetSdkTokenServerResponse ValueOf(String tokenString) {
        var data = new GetSdkTokenServerResponse();
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
