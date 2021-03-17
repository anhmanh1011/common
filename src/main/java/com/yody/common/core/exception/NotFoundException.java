package com.yody.common.core.exception;

import com.yody.common.enums.BaseEnum;
import com.yody.common.enums.CommonResponseCode;
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
        this.code = CommonResponseCode.NOT_FOUND.getValue();
        this.message = CommonResponseCode.NOT_FOUND.getDisplayName();
    }

    public NotFoundException(BaseEnum<Integer> enums){
        super(enums.getDisplayName(),enums.getValue());
        this.code = enums.getValue();
        this.message = enums.getDisplayName();
    }
}
