package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountStatusEnum implements BaseEnum<String> {
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

    public static AccountStatusEnum parse(String value) {
        for (AccountStatusEnum type : AccountStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
