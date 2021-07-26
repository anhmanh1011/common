package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShipmentPushStatusEnum implements BaseEnum<String> {

  WAITING("waiting","Đang đẩy đơn hàng"),
  FAILED("failed","Đẩy thông tin đơn hàng thất bại"),
  SUCCESS("success","Đẩy thông tin đơn hàng thành công");

  private String value;
  private String displayName;

  public static ShipmentPushStatusEnum parse(String value) {
    for (ShipmentPushStatusEnum o : ShipmentPushStatusEnum.values()) {
      if (o.getValue().equals(value)) {
        return o;
      }
    }
    return null;
  }

}
