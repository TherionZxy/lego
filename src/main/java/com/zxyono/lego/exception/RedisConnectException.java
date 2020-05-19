package com.zxyono.lego.exception;

public class RedisConnectException extends RuntimeException {
    public RedisConnectException() {
        super("Redis服务器连接异常");
    }
}