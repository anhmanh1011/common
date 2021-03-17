package com.yody.common.core.exception;

import com.yody.common.enums.CommonResponseCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternalServerException extends RuntimeException {
    private Integer code;
    private String message;

    public InternalServerException() {
        this.code = CommonResponseCode.INTERNAL_ERROR.getValue();
        this.message = CommonResponseCode.INTERNAL_ERROR.getDisplayName();
    }

}
