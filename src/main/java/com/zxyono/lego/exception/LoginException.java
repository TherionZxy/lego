package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class LoginException extends BaseException {
    public LoginException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}