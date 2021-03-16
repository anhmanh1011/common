package com.yody.common.core;

import com.yody.common.core.dto.Result;

public class BaseException extends RuntimeException {

    private Result result;

    public BaseException(Result result) {
        super(result.getMessage().toString());
        this.result = result;
    }
    public BaseException(String message){
        super(message);
    }

    public Result getResult(){
        return this.result;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}

