package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShippingServiceEnum implements BaseEnum<String> {

  EXPRESS("express", "Hỏa tốc"),
  STANDARD("standard", "Tiêu chuẩn"),
  FOUR_HOURS_DELIVERY("4h_delivery", "Giao hàng 4 giờ");

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

  public static ShippingServiceEnum parse(String value) {
    for (ShippingServiceEnum shippingServiceEnum : ShippingServiceEnum.values()) {
      if (shippingServiceEnum.getValue().equals(value)) {
        return shippingServiceEnum;
      }
    }
    return null;
  }
}
