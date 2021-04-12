package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency implements BaseEnum<String> {
  VND("VNĐ", "đ", 0, "Việt Nam Đồng");
  private String value;
  private String symbol;
  private Integer scale;
  private String displayName;

  public static Currency parse(String code) {
    for (Currency type : Currency.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }
}
