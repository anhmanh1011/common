package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyEnum implements BaseEnum<Integer> {
  VND(1,"VNĐ", "đ", 0, "Việt Nam Đồng"),
  THB(2,"THB","฿",2,"Thai Baht")
    ;
  private Integer value;
  private String code;
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
