package com.gameServer.gameBoot.model.register;


import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/31 12:42
 */
@Component
public class RegisterDataModelRequest {
    public String platformId;
    public String platfromToken;
    public String channelCode;
    public String version;
}
