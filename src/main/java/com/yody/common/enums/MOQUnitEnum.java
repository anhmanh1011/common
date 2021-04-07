package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MOQUnitEnum implements BaseEnum<Integer> {
    SINGLE(1, "Cái"),
    COUPLE(2, "Cặp"),
    KG(3, "Kg"),
    M(4, "m");

    private Integer  value;
    private String displayName;

    public static MOQUnitEnum parse(Integer value) {
        for (MOQUnitEnum type : MOQUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
