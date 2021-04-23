package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CollectionEnum implements BaseEnum<String>{
    YODY_EVERYDAY_WEAR("yody_everyday_wear","YODY EVERYDAY WEAR"),
    SUMMER("summer","SUMMER");

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

    public static CollectionEnum parse(String value) {
        for (CollectionEnum collection : CollectionEnum.values()) {
            if (collection.getValue().equals(value)) {
                return collection;
            }
        }
        return null;
    }
}
