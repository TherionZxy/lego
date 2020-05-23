package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class ParamException extends BaseException {
    public ParamException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}