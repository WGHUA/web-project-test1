package com.wgh.common.enums;

import lombok.Getter;

/**
 * @Author wughua
 * @Description ResultCode
 * @Date 2023/4/22
 */
public enum ResultCode {

    SUCCESS(200,"操作成功"),
    ERROR(500,"操作失败")
    ;

    @Getter
    private Integer code;

    @Getter
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
