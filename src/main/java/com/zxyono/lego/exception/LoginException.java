package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

/**
 * 针对管理员登录产生的异常
 */
public class LoginException extends BaseException {
    // 发生登录异常的用户名
    private String username;

    public LoginException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public LoginException(ExceptionEnum exceptionEnum, String username) {
        super(exceptionEnum.addMoreMessage(", 用户名：" + username));
    }
}