package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryServiceTypeEnum implements BaseEnum<String> {
  EMPLOYEE("employee", "Nhân viên"),
  EXTERNAL_SERVICE("external_service", "Dịch vụ bên ngoài"),
  EXTERNAL_SHIPPER("external_shipper", "Shipper bên ngoài");

  private final String value;
  private final String displayName;

  public static DeliveryServiceTypeEnum parse(String code) {
    for (DeliveryServiceTypeEnum type : DeliveryServiceTypeEnum.values()) {
      if (type.getValue().equals(code)) {
        return type;
      }
    }
    return null;
  }

}
