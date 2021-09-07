package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DSPStatusEnum implements BaseEnum<String> {
    //Delivery Service Provider Status
    ACTIVE("active", "Hoạt động"),
    INACTIVE("inactive", "Không hoạt đông");

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

    public static DSPStatusEnum parse(String value) {
        for (DSPStatusEnum type : DSPStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
