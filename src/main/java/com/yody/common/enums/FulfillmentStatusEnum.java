package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FulfillmentStatusEnum implements BaseEnum<String> {

  UNSHIPPED(1, "unshipped", "Chưa giao"),
  PICKED(2, "picked", "Đã lấy hàng"),
  PARTIAL(3, "partial", "Giao một phần"),
  PACKED(4, "packed", "Đã đóng gói"),
  SHIPPING(5, "shipping", "Đang giao"),
  SHIPPED(6, "shipped", "Đã giao"),
  RETURNING(7, "returning", "Đang trả lại"),
  RETURNED(8, "returned", "Đã trả lại"),
  CANCELLED(-1, "cancelled", "Đã hủy"),
  SPLITTED(-2, "splitted", "Đã tách");

  private final int intValue;
  private final String value;
  private final String displayName;

  public static FulfillmentStatusEnum parse(String value) {
    for (FulfillmentStatusEnum fulfillmentStatusEnum : FulfillmentStatusEnum.values()) {
      if (fulfillmentStatusEnum.getValue().equals(value)) {
        return fulfillmentStatusEnum;
      }
    }
    return null;
  }
}
