package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupplierStatusEnum implements BaseEnum<Integer> {
    ACTIVE(1, "Đang hoạt động"),
    INACTIVE(2, "Ngừng giao dịch");

    private Integer value;
    private String displayName;

    public static SupplierStatusEnum parse(Integer value) {
        for (SupplierStatusEnum type : SupplierStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
