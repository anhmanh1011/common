package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductUnitEnum implements BaseEnum<String>{
    PIECE("piece","Cái"),
    COUPLE("couple","Cặp"),
    SET("set","Bộ");
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

    public static ProductUnitEnum parse(String value) {
        for (ProductUnitEnum unit : ProductUnitEnum.values()) {
            if (unit.getValue().equals(value)) {
                return unit;
            }
        }
        return null;
    }
}
