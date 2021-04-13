package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductUnitEnum implements BaseEnum<Integer>{
    PIECE(1,"Cái"),
    SET(2,"Bộ");
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

    public static ProductUnitEnum parse(Integer value) {
        for (ProductUnitEnum unit : ProductUnitEnum.values()) {
            if (unit.getValue().equals(value)) {
                return unit;
            }
        }
        return null;
    }
}
