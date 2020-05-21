package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class AuthenticationException extends BaseException {
    public AuthenticationException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}