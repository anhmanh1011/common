package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AddressTypeEnum implements BaseEnum<String>{
	SUPPLIER_ADDRESS("supplier", "Địa chỉ xuất hàng"),
	BILLING_ADDRESS("billing", "Địa chỉ xuất hóa đơn");
	private final String value;
    private final String displayName;

    public static AddressTypeEnum parse(String value) {
        for (AddressTypeEnum type : AddressTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
