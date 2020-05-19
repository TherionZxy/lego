package com.zxyono.lego.exception;

public class JsonParseException extends RuntimeException {
    public JsonParseException() {
        super("Json字符串解析异常");
    }
}