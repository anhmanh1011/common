package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeeBaseOnEnum implements BaseEnum<String> {
  WEIGHT("weight","Cân nặng"),
  VOLUME("volume","Thể tích");
  private String value;
  private String displayName;

  public static FeeBaseOnEnum parse(String code) {
    for (FeeBaseOnEnum type : FeeBaseOnEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
