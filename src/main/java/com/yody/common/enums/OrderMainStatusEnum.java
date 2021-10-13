package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderMainStatusEnum implements BaseEnum<String> {
  DRAFT(1, "draft", "Đặt hàng"),
  FINALIZED(2, "finalized", "Xác nhận"),
  PACKED(3, "packed", "Đóng gói"),
  SHIPPING(4, "shipping", "Xuất kho"),
  COMPLETED(5, "completed", "Hoàn thành"),
  FINISHED(6, "finished", "Kết thúc"),
  CANCELLED(-1, "cancelled", "Hủy đơn");

  private final int intValue;
  private final String value;
  private final String displayName;

  public static OrderMainStatusEnum parse(String value) {
    for (OrderMainStatusEnum orderMainStatusEnum : OrderMainStatusEnum.values()) {
      if (orderMainStatusEnum.getValue().equals(value)) {
        return orderMainStatusEnum;
      }
    }
    return null;
  }
}
