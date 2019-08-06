package com.guli.common.vo;

import com.guli.common.constants.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyi
 * @describe 同意返回结果vo对象
 * @since 2019/8/2 - 14:34
 */
@Data
public class R {

    @ApiModelProperty(name = "返回状态码", value = "code")
    private Integer code;
    @ApiModelProperty(name = "操作成功与否", value = "success")
    private boolean success;
    @ApiModelProperty(name = "返回消息", value = "message")
    private String message;
    @ApiModelProperty(name = "返回数据", value = "data")
    private Map<String, Object> data = new HashMap<>();

    public static R ok(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static R error(){
        R r = new R();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static R setResult(ResultCodeEnum resultCodeEnum){
        R r = new R();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> date){
        this.setData(date);
        return this;
    }
}
