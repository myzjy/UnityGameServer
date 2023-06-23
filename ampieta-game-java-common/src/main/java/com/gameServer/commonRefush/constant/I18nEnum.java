package com.gameServer.commonRefush.constant;

/**
 * @version 0.0.1
 * @autor zjy
 * @since 2022/7/22 10:45 PM
 */
public enum I18nEnum {

    error_0("错误"),
    error_account_not_blank("账号为空"),
    error_account_not_exit("账号不存在"),
    error_account_password("账号或密码错误"),
    error_protocol_param("协议参数错误，请联系客服，我们会尽快解决"),
    error_user_orm_no_uid("token错误，请联系客服，我们会尽快解决"),
    error_account_password_not_affirm("两次密码不一致"),
    error_account_already_exists("账号已存在"),
    error_password_length("密码长度不符合要求"),
    error_password_not_have_null("密码包含空格，请重新设置"),
    error_start_login_not_setuid("请先登录"),
    error_login_process_not("登录流程内部错误，请联系客服"),
    error_uid_process_not("UID 错误，请联系客服"),
    ;
    private String message;

    I18nEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
