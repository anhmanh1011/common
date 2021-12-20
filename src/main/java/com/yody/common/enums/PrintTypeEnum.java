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
    ORDER_DRAFT("order_draft", "Đơn bán hàng nháp"),
    GOODS_RECEIPT_SEND("goods_receipt_send", "Biên bản bàn giao vận đơn rút gọn"),
    GOODS_RECEIPT_SEND_DETAIL("goods_receipt_send_detail", "Biên bản bàn giao vận đơn đầy đủ"),
    GOODS_RECEIPT_RECEIVE("goods_receipt_receive", "Biên bản nhận chuyển hoàn từ hãng vận chuyển rút gọn"),
    GOODS_RECEIPT_RECEIVE_DETAIL("goods_receipt_receive_detail", "Biên bản nhận chuyển hoàn từ hãng vận chuyển đầy đủ");

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
