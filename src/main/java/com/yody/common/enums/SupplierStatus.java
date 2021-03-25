package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupplierStatus implements BaseEnum<Integer> {
    ACTIVE(1, "Đang hoạt động"),
    INACTIVE(2, "Ngừng giao dịch");

    private Integer value;
    private String displayName;

    public static SupplierStatus parse(Integer value) {
        for (SupplierStatus type : SupplierStatus.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
