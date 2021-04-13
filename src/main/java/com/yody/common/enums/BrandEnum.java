package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BrandEnum implements BaseEnum<Integer>{
    YODY(1,"YODY"),
    ONOFF(2,"ONOFF");

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

    public static BrandEnum parse(Integer value) {
        for (BrandEnum brand : BrandEnum.values()) {
            if (brand.getValue().equals(value)) {
                return brand;
            }
        }
        return null;
    }
}
