package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ShippingFeeTypeEnum implements BaseEnum<String> {

  COD_FEE("cod_fee","Phí COD"),
  SHIP_FEE("ship_fee","Phí vận chuyển"),
  INSURANCE_FEE("insurance_fee","Phí bảo hiểm"),
  RETURN_FEE("return_fee","Phí hoàn trả"),
  OVERWEIGHT_SHIP_FEE("overweight_ship_fee","Phí vận chuyển quá tải");

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

  public static ShippingFeeTypeEnum parse(String value) {
    for (ShippingFeeTypeEnum shippingFeeTypeEnum : ShippingFeeTypeEnum.values()) {
      if (shippingFeeTypeEnum.getValue().equals(value)) {
        return shippingFeeTypeEnum;
      }
    }
    return null;
  }
}
