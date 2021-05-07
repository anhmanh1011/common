package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatusEnum implements BaseEnum<String>{
  DRAFT("draft","Nháp"),
  CONFIRMED("confirmed","Đã xác nhận"),
  IN_PROGRESS("in_progress","Đang xử lý"),
  COMPLETED("completed","Đã hoàn thành"),
  FINISHED("finished","Đã kết thúc"),
  CANCELLED("cancelled","Đã hủy"),
  EXPIRED("expired","Đã hết hạn");

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

  public static OrderStatusEnum parse(String value) {
    for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
      if (orderStatusEnum.getValue().equals(value)) {
        return orderStatusEnum;
      }
    }
    return null;
  }
}
