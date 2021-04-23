package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupplierStatusEnum implements BaseEnum<String> {
    ACTIVE("active", "Đang hoạt động"),
    INACTIVE("inactive", "Ngừng giao dịch");

    private String value;
    private String displayName;

    public static SupplierStatusEnum parse(String value) {
        for (SupplierStatusEnum type : SupplierStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
