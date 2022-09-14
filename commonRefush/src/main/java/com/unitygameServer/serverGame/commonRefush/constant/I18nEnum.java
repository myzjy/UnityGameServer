package com.unitygameServer.serverGame.commonRefush.constant;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/22 10:45 PM
 */
public enum I18nEnum {

    error_0("错误"),
    error_account_not_exit("账号不存在"),
    error_account_password("账号或密码错误"),

    ;
    private String message;

    I18nEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
