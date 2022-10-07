package com.gameServer.commonRefush.constant;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/22 10:45 PM
 */
public enum I18nEnum {

    error_0("错误"),
    error_account_not_exit("账号不存在"),
    error_account_password("账号或密码错误"),
    error_protocol_param("协议参数错误，请联系客服，我们会尽快解决"),
    error_user_orm_no_uid("token错误，请联系客服，我们会尽快解决"),
    ;
    private String message;

    I18nEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
