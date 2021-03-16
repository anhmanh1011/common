package com.yody.common.core.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {
    private String message;
    private Integer code;

    public BadRequestException(String message, Integer code) {
        super(message, code);
        this.code = 400000001;
        this.message = "Nhập dữ liệu không đúng ";
    }
}
