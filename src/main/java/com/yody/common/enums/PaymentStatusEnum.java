package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentStatusEnum implements BaseEnum<String>{

  UNPAID("unpaid","Chưa trả"),
  PAID("paid","Đã trả"),
  PARTIAL_PAID("partial_paid","Đã trả một phần"),
  REFUNDING("refunding", "Đang hoàn lại"),
  REFUNDED("refunded","Đã hoàn lại");

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

  public static PaymentStatusEnum parse(String value) {
    for (PaymentStatusEnum paymentStatusEnum : PaymentStatusEnum.values()) {
      if (paymentStatusEnum.getValue().equals(value)) {
        return paymentStatusEnum;
      }
    }
    return null;
  }
}
