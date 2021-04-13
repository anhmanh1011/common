package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CollectionEnum implements BaseEnum<Integer>{
    COLLECTION1(1,"BST 1"),
    COLLECTION2(2,"BST 2"),
    COLLECTION3(3,"BST 3");

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
