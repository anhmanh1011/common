package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductTypeEnum implements BaseEnum<Integer>{
    COMBO(1,"Combo"),
    NORMAL(2,"Thường");

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

    public static ProductTypeEnum parse(Integer value) {
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
