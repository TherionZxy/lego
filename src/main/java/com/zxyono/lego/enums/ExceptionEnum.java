package com.zxyono.lego.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    PARAM_EXCEPTION(40001, "param exception", "参数错误"),
    NOT_LOGIN(40301, "authentication exception", "未登录"),
    ACCESS_DENIED(40302, "authentication exception", "拒绝访问"),
    JWT_EXCEPTION(40305, "authentication exception", "JWT异常"),
    WX_AUTH_FAILED(50002, "wx exception", "小程序权限认证失败"),
    JSON_PARSE_EXCEPTION(50003, "json parse exception", "Json解析失败"),
    USER_NOT_FOUND(50004, "login exception", "用户不存在"),
    USERNAME_OR_PASSWORD_WRONG(50005, "login exception", "用户名或密码错误");


    private Integer code;
    private String type;
    private String message;

    public ExceptionEnum addMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionEnum addMessage(String message, Object...args) {
        this.message = String.format(message, args);
        return this;
    }

}