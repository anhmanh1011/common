package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiscountSourceEnum implements BaseEnum<String> {

  MANUAL("manual","Thủ công"),
  PROMOTION("promotion","Khuyến mãi"),
  COUPON("coupon","Phiếu giảm giá");

  private final String value;
  private final String displayName;
  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public String getDisplayName() {
    return this.displayName;
  }

  public static DiscountSourceEnum parse(String value) {
    for (DiscountSourceEnum discountSourceEnum : DiscountSourceEnum.values()) {
      if (discountSourceEnum.getValue().equals(value)) {
        return discountSourceEnum;
      }
    }
    return null;
  }
}
