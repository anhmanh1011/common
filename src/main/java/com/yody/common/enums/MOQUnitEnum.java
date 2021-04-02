package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MOQUnitEnum implements BaseEnum<String> {
    SINGLE("s", "Cái"),
    COUPLE("c", "Cặp"),
    KG("kg", "Kg"),
    M("m", "m");

    private String  value;
    private String displayName;

    public static MOQUnitEnum parse(String value) {
        for (MOQUnitEnum type : MOQUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
