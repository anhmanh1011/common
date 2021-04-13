package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupplierTypeEnum implements BaseEnum<Integer> {
    PERSONAL(1, "Cá nhân"),
    ENTERPRISE(2, "Doanh nghiệp");
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
