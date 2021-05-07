package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShippingRequirementEnum implements BaseEnum<String>{

  OPEN_TRY("open_try","Mở và thử"),
  OPEN_NO_TRY("open_no_try","Mở và không thử"),
  NO_OPEN("no_open","Không mở");

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

  public static ShippingRequirementEnum parse(String value) {
    for (ShippingRequirementEnum shippingRequirementEnum : ShippingRequirementEnum.values()) {
      if (shippingRequirementEnum.getValue().equals(value)) {
        return shippingRequirementEnum;
      }
    }
    return null;
  }
}
