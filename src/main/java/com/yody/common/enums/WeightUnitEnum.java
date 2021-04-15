package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeightUnitEnum implements BaseEnum<Integer> {
    G(1, "G"),
    KG(2, "KG");
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
