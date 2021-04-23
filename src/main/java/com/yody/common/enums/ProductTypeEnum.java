package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductTypeEnum implements BaseEnum<String>{
    NORMAL("normal","Thường"),
    COMBO("combo","Combo");

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

    public static ProductTypeEnum parse(String value) {
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
