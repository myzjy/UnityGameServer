package com.gameServer.httpsServer.request.sdkToken;

import org.springframework.stereotype.Component;

/**
 * 检验sdkToken
 *
 * @author zjy
 * @version 1.0
 * @since 2023/11/3 11 53
 */
@Component
public class RefreshSdkTokenServerRequest {
    public String sdkToken="";
    public long uid = 0;
}
