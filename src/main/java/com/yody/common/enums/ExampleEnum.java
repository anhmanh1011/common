package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExampleEnum implements BaseEnum<Integer> {

  EXAMPLE(1, ""),
  EXAMPLE2(2, "");

  //
  private final int value;
  private final String displayName;

  @Override
  public Integer getValue() {
    return this.value;
  }

  @Override
  public String getDisplayName() {
    return this.displayName;
  }

  public boolean isExample() {
    return this == EXAMPLE;
  }

  public static ExampleEnum parse(Integer value) {
    for (ExampleEnum type : ExampleEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }

}
