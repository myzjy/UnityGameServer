package com.gameServer.gameBoot.controller;

import com.gameServer.commonRefush.result.BaseResponse;
import com.gameServer.commonRefush.result.CodeEnum;
import com.gameServer.gameBoot.model.register.RegisterDataModelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/30 0:19
 */
@Controller
@CrossOrigin
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @PostMapping(value = "/api/register")
    @ResponseBody
    public BaseResponse getResVersion(HttpServletRequest request, @RequestBody RegisterDataModelRequest cm) {
        logger.info("/api/getVersion");
        logger.info(cm.platformId);
        return BaseResponse.valueOf(CodeEnum.OK);
    }
}
