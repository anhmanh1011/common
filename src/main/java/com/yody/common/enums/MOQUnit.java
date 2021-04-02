package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MOQUnit implements BaseEnum<String> {
    SINGLE("s", "Cái"),
    COUPLE("c", "Cặp"),
    KG("kg", "Kg"),
    M("m", "m");

    private String  value;
    private String displayName;

    public static MOQUnit parse(Integer value) {
        for (MOQUnit type : MOQUnit.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
