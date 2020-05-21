package com.zxyono.lego.exception.handler;

import com.zxyono.lego.exception.AuthenticationException;
import com.zxyono.lego.exception.LoginException;
import com.zxyono.lego.util.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public ResultMap loginExceptionHandler(LoginException e) {
        return ResultMap.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResultMap authenticationExceptionHandler(AuthenticationException e) {
        return ResultMap.error(e.getCode(), e.getMessage());
    }
}