package com.gameServer.home.equipment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/9/25 11 07
 */
@Component
public class EquipmentController {
    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    public EquipmentController() {
        logger.info("[EquipmentController]");
    }
}
