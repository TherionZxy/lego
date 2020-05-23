package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;

public class DataBaseException extends BaseException {
    private String msg;

    public DataBaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }

    public DataBaseException(ExceptionEnum exceptionEnum, String msg) {
        super(exceptionEnum.addMoreMessage(msg));
    }
}