package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintGoodsReceiptVariableEnum implements BasePrintEnum<String> {

  GOODS_RECEIPT_ID("{goods_receipt_id}", "ID biên bản bàn giao", "1271810"),
  GOODS_RECEIPT_CREATED_ON("{goods_receipt_created_on}", "Ngày tạo biên bản bàn giao","18/12/2021"),
  GOODS_RECEIPT_DELIVERY_NAME("{goods_receipt_delivery_name}", "Đối tác", "Giao hàng nhanh"),
  GOODS_RECEIPT_QUANTITY_ORDER("{goods_receipt_quantity_order}", "Số lượng đơn","5"),
  GOODS_RECEIPT_TOTAL("{goods_receipt_total}", "Tiền thu hộ","500,000"),
  GOODS_RECEIPT_DATE_PRINT("{goods_receipt_date_print}", "Ngày giờ in","30-08-2016 15:27:59");

  private final String value;
  private final String displayName;
  private final String previewValue;

  public static PrintGoodsReceiptVariableEnum parse(String value) {
    for (PrintGoodsReceiptVariableEnum type : PrintGoodsReceiptVariableEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
