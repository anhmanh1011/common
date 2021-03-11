package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatusEnum implements BaseEnum<Integer> {
    ACTIVE(1, "Đang hoạt động"),
    LOCK(2, "Khóa tạm thời"),
    BLOCKED(3, "Khóa vĩnh viễn"),
    INVENTORY_CHECKING(4, "Đang kiểm kho");

    private final int value;
    private final String displayName;
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public static StoreStatusEnum parse (int status) {
        for(StoreStatusEnum statusStore: StoreStatusEnum.values()) {
            if(statusStore.getValue() == status) {
                return statusStore;
            }
        }
        return null;
    }
}

