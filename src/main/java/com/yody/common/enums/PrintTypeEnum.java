package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintTypeEnum implements BaseEnum<String> {
    ORDER("order", "Hoá đơn bán hàng"),
    INVENTORY_TRANSFER("inventory_transfer", "Mẫu in phiếu chuyển kho"),
    INVENTORY_TRANSFER_BILL("inventory_transfer_bill", "Mẫu in vận đơn chuyển kho"),
    INVENTORY_ADJUSTMENT("inventory_adjustment", "Mẫu in biên bản xử lý hàng thừa thiếu"),
    SHIPMENT("shipment", "Phiếu giao hàng"),
    STOCK_EXPORT("stock_export", "Phiếu xuất kho"),
    ORDER_DRAFT("order_draft", "Đơn bán hàng nháp"),
    GOODS_RECEIPT_SEND("goods_receipt_send", "Biên bản bàn giao vận đơn rút gọn"),
    GOODS_RECEIPT_SEND_DETAIL("goods_receipt_send_detail", "Biên bản bàn giao vận đơn đầy đủ"),
    GOODS_RECEIPT_RECEIVE("goods_receipt_receive", "Biên bản nhận chuyển hoàn từ hãng vận chuyển rút gọn"),
    GOODS_RECEIPT_RECEIVE_DETAIL("goods_receipt_receive_detail", "Biên bản nhận chuyển hoàn từ hãng vận chuyển đầy đủ"),
    ORDER_EXCHANGE("order_exchange", "Hóa đơn đổi hàng"),
    ORDER_RETURN("order_return", "Hóa đơn trả hàng"),
    WARRANTY("warranty","Phiếu bảo hành"),
    PURCHASE_ORDER("purchase_order", "Hoá đơn nhập hàng"),
    PURCHASE_ORDER_7("purchase_order_ma_7","Hóa đơn in theo 1 mã 7"),
    PURCHASE_ORDER_FGG("purchase_order_fgg","Phiếu bảo hành FGG"),
    PURCHASE_ORDER_FGG_7("purchase_order_ma_7_fgg","Hóa đơn in theo 1 mã 7 FGG");

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
