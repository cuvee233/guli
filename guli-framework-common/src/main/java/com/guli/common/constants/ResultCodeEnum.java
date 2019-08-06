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
    SUCCESS(20000, "SUCCESS", true),
    BAD_SQL_GRAMMAR(20001, "BAD_SQL_GRAMMAR", false),
    JSON_PARSE_EXCEPTION(20001, "JSON_PARSE_EXCEPTION", false),
    SQL_EXCEPTION(20001, "SQL_EXCEPTION", false);
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
