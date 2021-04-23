package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LengthUnitEnum implements BaseEnum<String> {
    MM("mm", "Mm"),
    CM("cm", "Cm"),
    DM("dm", "Dm"),
    M("m", "M"),
    KM("km", "Km");

    private String  value;
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
