package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenderEnum implements BaseEnum<String>{
    MALE("male","Nam"),
    FEMALE("female","Nữ"),
    OTHER("other","Khác");

    private final String value;
    private final String displayName;
    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    public static GenderEnum parse(String value) {
        for (GenderEnum type : GenderEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
