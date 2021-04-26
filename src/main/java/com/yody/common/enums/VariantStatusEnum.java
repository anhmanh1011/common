package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VariantStatusEnum implements BaseEnum<String> {
  ACTIVE("active", "Đang bán"),
  IN_ACTIVE("inactive", "Ngừng bán");

  private String value;
  private String displayName;

  public static VariantStatusEnum parse(String value) {
    for (VariantStatusEnum v : VariantStatusEnum.values()) {
      if (v.getValue().equals(value)) {
        return v;
      }
    }
    return null;
  }
}
