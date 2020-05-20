package com.zxyono.lego.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    PARAM_EXCEPTION(40001, "param exception", "Wrong request parameter"),
    NOT_LOGIN(40301, "authentication exception", "Not login"),
    ACCESS_DENIED(40302, "authentication exception", "Access denied, insufficient permission"),
    JWT_EXCEPTION(40305, "authentication exception", "Jwt exception"),
    WX_AUTH_FAILED(50002, "wx exception", "WX auth failed"),
    JSON_PARSE_EXCEPTION(50003, "json parse exception", "Json parse failed");

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