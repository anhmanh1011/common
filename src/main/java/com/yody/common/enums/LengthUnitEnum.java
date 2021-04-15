package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LengthUnitEnum implements BaseEnum<Integer> {
    MM(1, "Mm"),
    CM(2, "Cm"),
    DM(3, "Dm"),
    M(4, "M"),
    KM(5, "Km");

    private Integer  value;
    private String displayName;

    public static WeightUnitEnum parse(WeightUnitEnum value) {
        for (WeightUnitEnum type : WeightUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
