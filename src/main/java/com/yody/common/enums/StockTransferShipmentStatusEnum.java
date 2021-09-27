package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StockTransferShipmentStatusEnum implements BaseEnum<String> {
  UNSHIPPED("unshipped","Chưa giao"),
  SHIPPING("shipping","Đang giao"),
  SHIPPED("shipped","Đã giao"),
  CANCELLED("cancelled","Đã hủy"),
  RETURNING("returning","Đang trả lại"),
  RETURNED("returned","Đã trả lại");

  private final String value;
  private final String displayName;
}
