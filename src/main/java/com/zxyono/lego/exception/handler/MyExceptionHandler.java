package com.zxyono.lego.exception.handler;

import com.zxyono.lego.exception.LoginException;
import com.zxyono.lego.exception.RedisException;
import com.zxyono.lego.exception.WechatException;
import com.zxyono.lego.util.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class MyExceptionHandler {
    /**
     * 捕获所有WechatException，然后以json形式返回给前端
     * @param e
     * @return
     */
    @ExceptionHandler(WechatException.class)
    public ResultMap wechatExceptionHandler(WechatException e) {
        return ResultMap.error(e.getCode(), e.getMessage(), e.getType());
    }

    /**
     * 捕获所有LoginException，然后以json形式返回给前端
     * @param e
     * @return
     */
    @ExceptionHandler(LoginException.class)
    public ResultMap loginExceptionHandler(LoginException e) {
        return ResultMap.error(e.getCode(), e.getMessage(), e.getType());
    }

    /**
     * 捕获RedisException，然后以json形式返回给前端
     * @param e
     * @return
     */
    @ExceptionHandler(RedisException.class)
    public ResultMap redisExceptionHandler(RedisException e) {
        return ResultMap.error(e.getCode(), e.getMessage(), e.getType());
    }
}