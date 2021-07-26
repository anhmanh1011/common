package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyEnum implements BaseEnum<String> {
  VND("VNĐ", "đ", 0, "Việt Nam Đồng"),
  THB("THB","฿",2,"Thai Baht");
  private String value;
  private String symbol;
  private Integer scale;
  private String displayName;

  public static CurrencyEnum parse(String code) {
    for (CurrencyEnum type : CurrencyEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }
}
