package com.yody.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryStatusEnum implements BaseEnum<String> {
    SHOW("show", "Hiển thị"),
    HIDE("hide", "Ẩn");

    private final String value;
    private final String displayName;

    public static CategoryStatusEnum parse(String value) {
        for (CategoryStatusEnum type : CategoryStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
