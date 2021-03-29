package com.yody.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryStatusEnum implements BaseEnum<Integer> {
    SHOW(1, "Hiển thị"),
    HIDE(1, "Ẩn");

    private final Integer value;
    private final String displayName;

    public static CategoryStatusEnum parse(Integer value) {
        for (CategoryStatusEnum type : CategoryStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
