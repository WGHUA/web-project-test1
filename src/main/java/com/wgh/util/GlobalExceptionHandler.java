package com.wgh.util;

import com.wgh.common.exception.ChainException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author wughua
 * @Description GlobalExceptionHandler
 * @Date 2023/4/21
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChainException.class)
    public String ChainException(ChainException e) {
        return "责任链发生异常:" + e.getErrorMsg();
    }
}
