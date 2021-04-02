package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateUnit implements BaseEnum<String> {
    DAY("d", "Ngày"),
    MONTH("m", "Tháng"),
    YEAR("y", "Năm");

    private String  value;
    private String displayName;

    public static DateUnit parse(Integer value) {
        for (DateUnit type : DateUnit.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
