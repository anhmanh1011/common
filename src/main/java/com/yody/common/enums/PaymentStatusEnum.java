package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatusEnum implements BaseEnum<String> {

  UNPAID(1, "unpaid", "Chưa trả"),
  PAID(2, "paid", "Đã trả"),
  PARTIAL_PAID(3, "partial_paid", "Đã trả một phần"),
  REFUNDING(4, "refunding", "Đang hoàn lại"),
  REFUNDED(5, "refunded", "Đã hoàn lại");

  private final int intValue;
  private final String value;
  private final String displayName;

  public static PaymentStatusEnum parse(String value) {
    for (PaymentStatusEnum paymentStatusEnum : PaymentStatusEnum.values()) {
      if (paymentStatusEnum.getValue().equals(value)) {
        return paymentStatusEnum;
      }
    }
    return null;
  }
}
