package com.gameServer.gameBoot.debugLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/api/log/")
public class DebugLogController {
    private static final Logger logger = LoggerFactory.getLogger(DebugLogController.class);

    @PostMapping("upload/log")
    public void UploadClientLogShow() {
        logger.info("调用上次log");
    }

}
