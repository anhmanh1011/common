package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TaxTreatmentEnum implements BaseEnum<String> {

  INCLUSIVE("inclusive","Đã tính thuế"),
  EXCLUSIVE("exclusive","Chưa tính thuế");

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

  public static TaxTreatmentEnum parse(String value) {
    for (TaxTreatmentEnum taxTreatmentEnum : TaxTreatmentEnum.values()) {
      if (taxTreatmentEnum.getValue().equals(value)) {
        return taxTreatmentEnum;
      }
    }
    return null;
  }
}
