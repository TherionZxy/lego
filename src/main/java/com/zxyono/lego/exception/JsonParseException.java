package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class JsonParseException extends BaseException {
    public JsonParseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}