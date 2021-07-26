package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryServiceEnum implements BaseEnum<String> {

  GHN("ghn","Giao hàng nhanh","https://ghn.vn/blogs/trang-thai-don-hang"),

  GHTK("ghtk","Giao hàng tiết kiệm","https://i.ghtk.vn"),

  VTP("vtp","VietelPost","https://viettelpost.com.vn/tra-cuu-hanh-trinh-don/"),

  DHL("dhl","DHL","https://mydhl.express.dhl/vn/vi/home.html#/createNewShipmentTab");

  private String value;
  private String displayName;
  private String trackingUrl;

  public static DeliveryServiceEnum parse(String value) {
    for (DeliveryServiceEnum o : DeliveryServiceEnum.values()) {
      if (o.getValue().equals(value)) {
        return o;
      }
    }
    return null;
  }

}
