package com.yody.common.core.exception;

import com.yody.common.core.constant.CommonErrors;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternalServerException extends RuntimeException {
    private Integer code;
    private String message;

    public InternalServerException() {
        this.code = CommonErrors.INTERNAL_SERVER_ERROR.getCode();
        this.message = CommonErrors.INTERNAL_SERVER_ERROR.getMessage();
    }
}
