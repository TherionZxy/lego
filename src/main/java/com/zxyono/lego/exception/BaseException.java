package com.zxyono.lego.exception;

import com.zxyono.lego.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private Integer code;
    private String type;

    public BaseException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
        this.type = exceptionEnum.getType();
    }
}