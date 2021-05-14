package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MediaFileStatusEnum implements BaseEnum<String> {

  ACTIVE("active","Hoạt động"),
  INACTIVE("inactive","Không hoạt động"),
  DISABLE("disable","Vô hiệu hóa"),
  DELETED("deleted","Đã xóa");

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

  public static MediaFileStatusEnum parse(String value) {
    for (MediaFileStatusEnum type : MediaFileStatusEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
