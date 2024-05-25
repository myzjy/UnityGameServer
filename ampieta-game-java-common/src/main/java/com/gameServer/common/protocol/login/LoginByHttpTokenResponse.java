package com.gameServer.common.protocol.login;

import com.zfoo.protocol.anno.Note;
import com.zfoo.protocol.anno.Protocol;

/**
 * @author zjy
 * @version 1.0
 * @since 2024/2/2 14 25
 */
@Protocol(id = 1016)
public class LoginByHttpTokenResponse {
    @Note("信息")
    private String message;

    public static LoginByHttpTokenResponse valueOf(String message) {
        var response = new LoginByHttpTokenResponse();
        response.message = message;
        return response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
