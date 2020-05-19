package com.zxyono.lego.exception;

public class HttpRequestException extends RuntimeException {
    public HttpRequestException() {
        super("服务器端HTTP请求异常");
    }
}