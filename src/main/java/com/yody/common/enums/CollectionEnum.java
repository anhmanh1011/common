package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CollectionEnum implements BaseEnum<Integer>{
    YODYEVERYDAYWEAR(1,"YODYEVERYDAYWEAR"),
    SUMMER(2,"SUMMER");

    private final int value;
    private final String displayName;
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    public static CollectionEnum parse(Integer value) {
        for (CollectionEnum collection : CollectionEnum.values()) {
            if (collection.getValue().equals(value)) {
                return collection;
            }
        }
        return null;
    }
}
