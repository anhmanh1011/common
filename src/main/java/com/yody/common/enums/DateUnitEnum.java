package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateUnitEnum implements BaseEnum<Integer> {
    DAY(1, "Ngày"),
    MONTH(2, "Tháng"),
    YEAR(3, "Năm");

    private Integer  value;
    private String displayName;

    public static DateUnitEnum parse(Integer value) {
        for (DateUnitEnum type : DateUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
