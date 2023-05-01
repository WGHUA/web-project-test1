package com.wgh.common;

import com.wgh.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 通用结果返回
 *
 * @author wgh
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5969315653647465119L;
    private Integer code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public Result(ResultCode resultCode, T data) {
        this(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public Result(Integer code, String message) {
        this(code, message, null);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
