package com.yody.common.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CommonErrors {
    INTERNAL_SERVER_ERROR(50000000, "Hệ thống gặp vấn đề"),
    BAD_REQUEST_ERROR(40000000, "Dữ liệu nhập vào không chính xác"),
    NOT_FOUND_ERROR(40400000, "Không tìm thấy dữ liệu")
    ;
    private Integer code;
    private String message;

}
