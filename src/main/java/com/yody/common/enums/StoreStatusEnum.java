package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StoreStatusEnum implements BaseEnum<String> {
    ACTIVE("active", "Đang hoạt động"),
    TEMP_LOCK("temp_lock", "Khóa tạm thời"),
    PERMANENT_LOCK("permanent_lock", "Khóa vĩnh viễn"),
    INVENTORY_CHECKING("checking", "Đang kiểm kho");

    private final String value;
    private final String displayName;
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    public static StoreStatusEnum parse (String status) {
        for(StoreStatusEnum statusStore: StoreStatusEnum.values()) {
            if(statusStore.getValue().equals(status)) {
                return statusStore;
            }
        }
        return null;
    }
}

