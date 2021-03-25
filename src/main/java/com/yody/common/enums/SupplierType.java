package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupplierType implements BaseEnum<Integer> {
    PERSONAL(1, "personal"),
    ENTERPRISE(2, "enterprise");
    private final Integer value;
    private final String displayName;

    public static SupplierType parse(Integer value) {
        for (SupplierType type : SupplierType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
