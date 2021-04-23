package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateUnitEnum implements BaseEnum<String> {
    DAY("day", "Ngày"),
    MONTH("month", "Tháng"),
    YEAR("year", "Năm");

    private String  value;
    private String displayName;

    public static DateUnitEnum parse(String value) {
        for (DateUnitEnum type : DateUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
