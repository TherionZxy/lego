package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

/**
 * 与Redis相关的异常
 */
public class RedisException extends BaseException {
    public RedisException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}