package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    VND("VNĐ","đ",0,"Việt Nam Đồng");

    private String code;
    private String symbol;
    private Integer scale;
    private String name;

    public static Currency parse(String code) {
        for (Currency type : Currency.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
}
