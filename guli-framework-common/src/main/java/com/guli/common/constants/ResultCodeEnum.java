package com.guli.common.constants;

import com.sun.net.httpserver.Authenticator;
import lombok.Data;
import lombok.Getter;

/**
 * @author weiyi
 * @describe 统一返回状态码枚举类
 * @since 2019/8/2 - 14:37
 */
@Getter
public enum ResultCodeEnum {

    UNKNOWN_REASON(20001, "FAILED", false),
    SUCCESS(20000, "SUCCESS", true);
    private Integer code;
    private String message;
    private boolean success;

    private ResultCodeEnum(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public boolean getSuccess() {
        return success;
    }
}
