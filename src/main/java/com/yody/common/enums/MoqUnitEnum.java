package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MoqUnitEnum implements BaseEnum<String> {
    PIECE("single", "Cái"),
    COUPLE("couple", "Cặp"),
    KG("kg", "Kg"),
    M("m", "m");

    private String  value;
    private String displayName;

    public static MoqUnitEnum parse(String value) {
        for (MoqUnitEnum type : MoqUnitEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
