package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryServiceStatusEnum implements BaseEnum<String> {
  UNSHIPPED("unshipped","Chưa giao"),
  PICKED("picked","Đã lấy hàng"),
  PARTIAL("partial","Giao một phần"),
  PACKED("packed","Đã đóng gói"),
  SHIPPING("shipping","Đang giao"),
  SHIPPED("shipped","Đã giao"),
  CANCELLED("cancelled","Đã hủy"),
  RETURNING("returning","Đang trả lại"),
  RETURNED("returned","Đã trả lại");
  private String value;
  private String displayName;

  public static DeliveryServiceStatusEnum parse(String code) {
    for (DeliveryServiceStatusEnum type : DeliveryServiceStatusEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
