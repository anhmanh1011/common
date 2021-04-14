package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductPriceType implements BaseEnum<Integer> {
  PRICE(1, "PRICE"),
  AVG_COST(2, "AVG_COST"),
  IMPORT_PRICE(3, "IMPORT_PRICE"),
  WHOLE_SALE_PRICE(4, "WHOLE_SALE_PRICE");

  private Integer value;
  private String displayName;

  public static ProductPriceType parse(Integer value) {
    for (ProductPriceType type : ProductPriceType.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
