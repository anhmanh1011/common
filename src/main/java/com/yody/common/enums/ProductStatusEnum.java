package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatusEnum implements BaseEnum<Integer> {
  ACTIVE(1, "Đang bán"),
  IN_ACTIVE(2, "Ngừng bán"),
  DELETED(3, "Đã xóa");

  private Integer value;
  private String displayName;

  public static ProductStatusEnum parse(int value) {
    for (ProductStatusEnum v : ProductStatusEnum.values()) {
      if (v.getValue().equals(value)) {
        return v;
      }
    }
    return null;
  }
}
