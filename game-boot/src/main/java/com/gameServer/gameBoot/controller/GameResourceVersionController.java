package com.gameServer.gameBoot.controller;

import com.gameServer.commonRefush.result.BaseResponse;
import com.gameServer.commonRefush.result.CodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/5 14:02
 */
@Controller
@CrossOrigin
@RequestMapping("/api")
public class GameResourceVersionController {
    private static final Logger logger = LoggerFactory.getLogger(GameResourceVersionController.class);

    @GetMapping("/getVersion")
    @ResponseBody
    public BaseResponse getResVersion() {
        logger.info("/api/getVersion");
        return BaseResponse.valueOf(CodeEnum.OK);
    }

    @GetMapping("/saveVersion")
    @ResponseBody
    public BaseResponse SaveResVersion() {
        return BaseResponse.valueOf(CodeEnum.OK);
    }

    void Get() {

    }
}

