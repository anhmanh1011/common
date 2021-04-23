package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BrandEnum implements BaseEnum<String>{
    YODY("yody","YODY"),
    ONOFF("onoff","ONOFF");

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

    public static BrandEnum parse(String value) {
        for (BrandEnum brand : BrandEnum.values()) {
            if (brand.getValue().equals(value)) {
                return brand;
            }
        }
        return null;
    }
}
