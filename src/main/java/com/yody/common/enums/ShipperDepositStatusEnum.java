package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShipperDepositStatusEnum implements BaseEnum<String> {
  ACTIVE("active", "Đang hoạt động"),
  CANCELLED("cancelled","Đã hủy");
  private String value;
  private String displayName;

  public static ShipperDepositStatusEnum parse(String code) {
    for (ShipperDepositStatusEnum type : ShipperDepositStatusEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
