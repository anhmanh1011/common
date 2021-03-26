package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
enum SupplierTypeEnum implements BaseEnum<Integer> {
    PERSONAL(1, "personal"),
    ENTERPRISE(2, "enterprise");
    private final Integer value;
    private final String displayName;

    public static SupplierTypeEnum parse(Integer value) {
        for (SupplierTypeEnum type : SupplierTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
