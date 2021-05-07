package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeliveryTypeEnum implements BaseEnum<String> {

  PICK_UP("pickup","Tự gửi"),
  DELIVERY_BY_3PL("delivery_by_3pl","Vận chuyển bên thứ 3"),
  DROP_SHIPPING("drop_shipping","Drop shipping");

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

  public static DeliveryTypeEnum parse(String value) {
    for (DeliveryTypeEnum deliveryTypeEnum : DeliveryTypeEnum.values()) {
      if (deliveryTypeEnum.getValue().equals(value)) {
        return deliveryTypeEnum;
      }
    }
    return null;
  }
}
