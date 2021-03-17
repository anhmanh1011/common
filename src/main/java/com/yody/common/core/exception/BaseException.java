package com.yody.common.core.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException() {

    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

