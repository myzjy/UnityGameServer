package com.gameServer.httpsServer.SdkToken.controller;

import com.gameServer.common.util.TokenUtils;
import com.gameServer.httpsServer.request.sdkToken.GetSdkTokenServerRequest;
import com.gameServer.httpsServer.request.sdkToken.RefreshSdkTokenServerRequest;
import com.gameServer.httpsServer.response.sdkToken.GetSdkTokenServerResponse;
import com.gameServer.httpsServer.response.sdkToken.RefreshSdkTokenServerResponse;
import com.zfoo.protocol.util.StringUtils;
import com.zfoo.scheduler.util.TimeUtils;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/10/29 15 42
 */
@Component
@RestController
@RequestMapping("/api")
public class SDKTokenController {
    private static final Logger logger = LoggerFactory.getLogger(SDKTokenController.class);

    @Bean
    @RequestMapping(value = "/getSdkToken", method = RequestMethod.POST)
    @ResponseBody
    public GetSdkTokenServerResponse getResVersion(
            @RequestBody @NotNull
            GetSdkTokenServerRequest request) {
        logger.info("获取");
        return GetSdkTokenServerResponse.ValueOf(TokenUtils.set(request.uid));
    }

    @Bean
    @RequestMapping(value = "/refreshSdkToken", method = RequestMethod.POST)
    @ResponseBody
    public RefreshSdkTokenServerResponse ReturnRefreshSdkToken(
            @RequestBody
            RefreshSdkTokenServerRequest request
    ) {
        if (StringUtils.isEmpty(request.sdkToken)) {
            return RefreshSdkTokenServerResponse.ValueOf(request.sdkToken);
        }
        var tokenTriple = TokenUtils.get(request.sdkToken);
        var salt = tokenTriple.getMiddle();
        var expirationTimeLong = tokenTriple.getRight();
        var nowLong = TimeUtils.now();
        logger.info("当前token：{},[当前uid：{}][salt:{}]", tokenTriple, request.uid, salt);
        logger.info("[expirationTimeLong:{}],[nowLog:{}][当前token是否过期：{}][expirationTimeLong:{}]", TimeUtils.timeToString(expirationTimeLong), TimeUtils.timeToString(nowLong), nowLong > expirationTimeLong, expirationTimeLong);
        if (nowLong > expirationTimeLong) {
            //代表过时的token
            var token = TokenUtils.set(request.uid);
            logger.info("[{}]重新设置Token:[{}]", request.uid, token);
            return RefreshSdkTokenServerResponse.ValueOf(token);
        }
        return RefreshSdkTokenServerResponse.ValueOf(request.sdkToken);
    }
}
