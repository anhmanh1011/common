package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LengthUnitEnum implements BaseEnum<String> {
  CM("cm", "Cm"),
  MM("mm", "Mm"),
  M("m", "M");

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
