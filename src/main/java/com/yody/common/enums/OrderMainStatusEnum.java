package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderMainStatusEnum implements BaseEnum<String>{
  DRAFT("draft","Đặt hàng"),
  FINALIZED("finalized","Xác nhận"),
  PACKED("packed","Đóng gói"),
  SHIPPING("shipping","Xuất kho"),
  COMPLETED("completed","Hoàn thành"),
  FINISHED("finished","Kết thúc"),
  CANCELLED("cancelled","Hủy đơn");

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

  public static OrderMainStatusEnum parse(String value) {
    for (OrderMainStatusEnum orderMainStatusEnum : OrderMainStatusEnum.values()) {
      if (orderMainStatusEnum.getValue().equals(value)) {
        return orderMainStatusEnum;
      }
    }
    return null;
  }
}
