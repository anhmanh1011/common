package com.yody.common.core.exception;

import com.yody.common.core.constant.CommonErrors;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {
    private String message;
    private Integer code;

    public BadRequestException(String message, Integer code) {
        super(message, code);
        this.code = code;
        this.message = message;
    }

    public BadRequestException() {
        this.code = CommonErrors.BAD_REQUEST_ERROR.getCode();
        this.message = CommonErrors.BAD_REQUEST_ERROR.getMessage();
    }
}
