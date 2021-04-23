package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SupplierTypeEnum implements BaseEnum<String> {
    PERSONAL("personal", "Cá nhân"),
    ENTERPRISE("enterprise", "Doanh nghiệp");
    private final String value;
    private final String displayName;

    public static SupplierTypeEnum parse(String value) {
        for (SupplierTypeEnum type : SupplierTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
