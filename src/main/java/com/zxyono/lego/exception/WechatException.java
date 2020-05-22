package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

/**
 * 与微信相关的异常
 */
public class WechatException extends BaseException {
    public WechatException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public WechatException(ExceptionEnum exceptionEnum, String errmsg) {
        super(exceptionEnum.addMoreMessage(errmsg));
    }
}