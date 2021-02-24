package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GenderEnum implements BaseEnum<Integer>{
    MALE(1,"enums.gender.male"),
    FEMALE(2,"enums.gender.female"),
    OTHER(2,"enums.gender.other");

    private final int value;
    private final String displayName;
    @Override
    public Integer getValue() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    public static GenderEnum parse(Integer value) {
        for (GenderEnum type : GenderEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
