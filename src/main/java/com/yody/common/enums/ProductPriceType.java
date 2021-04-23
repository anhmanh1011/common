package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductPriceType implements BaseEnum<String> {
  RETAIL_PRICE("retail_price", "Giá bán lẻ"),
  COGS("cogs", "Giá vốn"),
  IMPORT_PRICE("import_price", "Giá nhập"),
  WHOLE_SALE_PRICE("whole_sale_price", "Giá bán buôn");

  private String value;
  private String displayName;

  public static ProductPriceType parse(String value) {
    for (ProductPriceType type : ProductPriceType.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
