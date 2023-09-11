package com.gameServer.common.result;

/**
 * @author Administrator
 * @version 1.0
 * @since 2022/10/5 15:46
 */
public enum CodeEnum {
    /**
     * 通用错误码
     */
    FAIL(0, "请求失败"),
    OK(1, "请求成功"),
    ;

    private final int code;
    private final String message;

    CodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
