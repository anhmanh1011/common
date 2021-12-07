package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintTypeEnum implements BaseEnum<String> {
    ORDER("order", "Hoá đơn bán hàng"),
    PURCHASE_ORDER("purchase_order", "Hoá đơn nhập hàng"),
    SHIPMENT("shipment", "Phiếu giao hàng"),
    STOCK_EXPORT("stock_export", "Phiếu xuất kho"),
    ORDER_DRAFT("order_draft", "Đơn bán hàng nháp");

    private final String  value;
    private final String displayName;

    public static PrintTypeEnum parse(String value) {
        for (PrintTypeEnum type : PrintTypeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
