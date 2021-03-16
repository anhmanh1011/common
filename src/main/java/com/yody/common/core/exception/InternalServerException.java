package com.yody.common.core.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternalServerException extends RuntimeException {
    private Integer code;
    private String message;

    public InternalServerException() {
        this.code = 50000000;
        this.message = "Internal Server Error";
    }
}
