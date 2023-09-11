package com.gameServer.gameBoot.controller;

import com.gameServer.common.result.BaseResponse;
import com.gameServer.common.result.CodeEnum;
import com.gameServer.gameBoot.model.register.RegisterDataModelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/30 0:19
 */

@Controller
@CrossOrigin
@RequestMapping("/register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Bean
    @RequestMapping(value = "/version", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getResVersion(@RequestBody RegisterDataModelRequest cm) {

        logger.info("/register/version");
        logger.info(cm.platformId);

        return BaseResponse.valueOf(CodeEnum.OK);
    }
}
