package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatusEnum implements BaseEnum<String> {
  ACTIVE("active", "Hoạt động"),
  IN_ACTIVE("inactive", "Dừng hoạt động");

  private String value;
  private String displayName;

  public static ProductStatusEnum parse(String value) {
    for (ProductStatusEnum v : ProductStatusEnum.values()) {
      if (v.getValue().equals(value)) {
        return v;
      }
    }
    return null;
  }
}
