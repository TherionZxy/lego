package com.zxyono.lego.exception.handler;

import com.zxyono.lego.enums.ExceptionEnum;
import com.zxyono.lego.exception.LoginException;
import com.zxyono.lego.exception.ParamException;
import com.zxyono.lego.exception.RedisException;
import com.zxyono.lego.exception.WechatException;
import com.zxyono.lego.util.ResultMap;
import io.lettuce.core.RedisCommandTimeoutException;
import io.lettuce.core.RedisConnectionException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
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

    /**
     * 特别的，如果Redis服务器连接不上时的异常处理
     * @return
     */
    @ExceptionHandler({RedisCommandTimeoutException.class, RedisConnectionException.class})
    public ResultMap redisCommadTimeoutExceptionHadler() {
        return ResultMap.error(ExceptionEnum.REDIS_SEVER_NOT_RUN);
    }

    /**
     * 捕获ParamException，然后以json形式返回给前端
     * @param e
     * @return
     */
    @ExceptionHandler(ParamException.class)
    public ResultMap paramExceptionHandler(ParamException e) {
        return ResultMap.error(e.getCode(), e.getMessage(), e.getType());
    }

    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public ResultMap jdbcSQLIntegrityConstraintViolationExceptionHandler(JdbcSQLIntegrityConstraintViolationException e) {
        return ResultMap.error(ExceptionEnum.MYSQL_UNIQUE_COLUMNS);
    }
}