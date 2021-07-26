package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DateTimeEnum implements BaseEnum<String> {
  yyyy_MM_dd("yyyy-MM-dd", "yyyy-MM-dd"),
  yyyy_MM_dd_HHmmss("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss"),
  yyyyMMdd_T_HHmmssSSSXXX("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
  yyyyMMdd_T_HHmmssSSSZ("yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
  ddMMyyyHHmmss("dd-MM-yyyy HH:mm:ss", "dd-MM-yyyy HH:mm:ss"),
  dd_MM_yyyy("dd-MM-yyyy", "dd-MM-yyyy"),
  ddMMyyyy("ddMMyyyy", "ddMMyyyy");

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

}

