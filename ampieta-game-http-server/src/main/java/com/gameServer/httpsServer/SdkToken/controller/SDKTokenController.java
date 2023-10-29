package com.gameServer.httpsServer.SdkToken.controller;

import com.gameServer.common.util.TokenUtils;
import com.gameServer.httpsServer.request.sdkToken.GetSdkTokenServerRequest;
import com.gameServer.httpsServer.response.sdkToken.GetSdkTokenServerResponse;
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
}
