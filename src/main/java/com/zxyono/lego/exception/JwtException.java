package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class JwtException extends BaseException {
    public JwtException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}