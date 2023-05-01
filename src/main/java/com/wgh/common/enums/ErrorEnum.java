package com.wgh.common.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author wughua
 * @Description ErrorEnum
 * @Date 2023/4/15
 */
public enum ErrorEnum {

    RESPONSIBILITY_CHAIN_ERROR("RESPONSIBILITY_CHAIN_ERROR","责任链异常:{}");

    @Getter
    private String code;

    @Getter
    private String msg;

    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
