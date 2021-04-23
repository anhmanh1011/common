package com.yody.common.core.exception;

import com.yody.common.enums.BaseEnum;
import com.yody.common.enums.CommonResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends BaseException {
    private String message;
    private Integer code;

    public BadRequestException(String message, Integer code) {
        super(message, code);
        this.code = code;
        this.message = message;
    }

    public BadRequestException() {
        this.code = CommonResponseCode.BAD_REQUEST.getValue();
        this.message = CommonResponseCode.BAD_REQUEST.getDisplayName();
    }

    public BadRequestException(String message){
        this.message = message;
        this.code  = CommonResponseCode.BAD_REQUEST.getValue();
    }

    public BadRequestException(BaseEnum<Integer> enums){
        super(enums.getDisplayName(),enums.getValue());
        this.code = enums.getValue();
        this.message = enums.getDisplayName();
    }

}
