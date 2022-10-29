package com.gameServer.gameBoot.controller;

import com.gameServer.commonRefush.result.BaseResponse;
import com.gameServer.commonRefush.result.CodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/30 0:19
 */
@Controller
@CrossOrigin
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @PostMapping("/api/register")
    @ResponseBody
    public BaseResponse getResVersion() {
        logger.info("/api/getVersion");
        return BaseResponse.valueOf(CodeEnum.OK);
    }
}
