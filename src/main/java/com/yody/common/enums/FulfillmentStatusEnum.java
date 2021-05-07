package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FulfillmentStatusEnum implements BaseEnum<String> {

  UNSHIPPED("unshipped","Chưa giao"),
  PICKED("picked","Đã lấy hàng"),
  PARTIAL("partial","Giao một phần"),
  PACKED("packed","Đã đóng gói"),
  SHIPPING("shipping","Đang giao"),
  SHIPPED("shipped","Đã giao"),
  CANCELLED("cancelled","Đã hủy"),
  RETURNING("returning","Đang trả lại"),
  RETURNED("returned","Đã trả lại");

  private final String value;
  private final String displayName;
  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public String getDisplayName() {
    return this.displayName;
  }

  public static FulfillmentStatusEnum parse(String value) {
    for (FulfillmentStatusEnum fulfillmentStatusEnum : FulfillmentStatusEnum.values()) {
      if (fulfillmentStatusEnum.getValue().equals(value)) {
        return fulfillmentStatusEnum;
      }
    }
    return null;
  }
}
