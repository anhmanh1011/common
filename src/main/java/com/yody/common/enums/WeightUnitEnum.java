package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeightUnitEnum implements BaseEnum<String> {
    G("g", "Gram"),
    KG("kg", "Kilogram");
    private String  value;
    private String displayName;

    public static WeightUnitEnum parse(String value) {
        for (WeightUnitEnum type : WeightUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
