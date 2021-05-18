package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WhoPaidEnum implements BaseEnum<String> {
  COMPANY("company","Công ty"),
  CUSTOMER("customer","Khách hàng");
  private String value;
  private String displayName;

  public static WhoPaidEnum parse(String code) {
    for (WhoPaidEnum type : WhoPaidEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
