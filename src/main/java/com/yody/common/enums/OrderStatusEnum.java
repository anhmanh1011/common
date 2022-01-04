package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatusEnum implements BaseEnum<String>{
  DRAFT("draft","Nháp"),
  FINALIZED("finalized","Xác nhận"),
  COMPLETED("completed","Hoàn thành"),
  FINISHED("finished","Kết thúc"),
  CANCELLED("cancelled","Hủy");

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
