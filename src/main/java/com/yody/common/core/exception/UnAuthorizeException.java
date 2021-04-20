package com.yody.common.core.exception;

import com.yody.common.enums.BaseEnum;
import com.yody.common.enums.CommonResponseCode;
import lombok.Getter;

@Getter
public class UnAuthorizeException extends BaseException {
    private String message;
    private Integer code;

    public UnAuthorizeException(String message, Integer code) {
        super(message, code);
        this.code = code;
        this.message = message;
    }

    public UnAuthorizeException() {
        this.code = CommonResponseCode.UNAUTHORIZE.getValue();
        this.message = CommonResponseCode.UNAUTHORIZE.getDisplayName();
    }

    public UnAuthorizeException(BaseEnum<Integer> enums) {
        super(enums.getDisplayName(), enums.getValue());
        this.code = enums.getValue();
        this.message = enums.getDisplayName();
    }
}
