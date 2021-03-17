package com.yody.common.core.exception;

import com.yody.common.core.constant.CommonErrors;
import lombok.Getter;

@Getter
public class NotFoundException extends BaseException {
    private String message;
    private Integer code;

    public NotFoundException(String message, Integer code) {
        super(message, code);
        this.code = code;
        this.message = message;
    }

    public NotFoundException() {
        this.code = CommonErrors.NOT_FOUND_ERROR.getCode();
        this.message = CommonErrors.NOT_FOUND_ERROR.getMessage();
    }
}
