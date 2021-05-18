package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceTypeEnum implements BaseEnum<String> {
  EXPRESS("express","Hoả tốc"),
  STANDARD("standard","Tiêu chuẩn");
  private String value;
  private String displayName;

  public static ServiceTypeEnum parse(String code) {
    for (ServiceTypeEnum type : ServiceTypeEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
