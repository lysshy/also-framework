package com.also.framework.security.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseResult implements Serializable {

    private String code;

    private String msg;

    private Object data;

    public BaseResult(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    public BaseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BaseResult ok(Object data) {
        BaseResult baseResult = new BaseResult(ResultCode.SUCCESS.getCode(), data);
        baseResult.setMsg(ResultCode.SUCCESS.getMsg());
        return baseResult;
    }

    public static BaseResult fail(String code, String msg) {
        return new BaseResult(code, msg);
    }

    public static BaseResult fail(ResultCode resultCode) {
        return new BaseResult(resultCode.getCode(), resultCode.getMsg());
    }
}
