package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderLineTypeEnum implements BaseEnum<String>{

  NORMAL("normal","Thường"),
  GIFT("gift","Quà"),
  SERVICE("service","Dịch vụ");

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

  public static OrderLineTypeEnum parse(String value) {
    for (OrderLineTypeEnum orderLineTypeEnum : OrderLineTypeEnum.values()) {
      if (orderLineTypeEnum.getValue().equals(value)) {
        return orderLineTypeEnum;
      }
    }
    return null;
  }
}
