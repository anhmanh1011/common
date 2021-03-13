package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountStatusEnum implements BaseEnum<Integer> {
    ACTIVE(1, "Hoạt động"),
    INACTIVE(2, "Không hoạt đông");
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

    public static AccountStatusEnum parse(Integer value) {
        for (AccountStatusEnum type : AccountStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
