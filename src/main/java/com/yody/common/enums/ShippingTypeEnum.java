package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShippingTypeEnum implements BaseEnum<String> {

  DELIVER_TO_CUSTOMER("deliver_to_customer","Vận chuyển tới khách hàng"),
  RETURN("return","Trả lại hàng");

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

  public static ShippingTypeEnum parse(String value) {
    for (ShippingTypeEnum shippingTypeEnum : ShippingTypeEnum.values()) {
      if (shippingTypeEnum.getValue().equals(value)) {
        return shippingTypeEnum;
      }
    }
    return null;
  }
}
