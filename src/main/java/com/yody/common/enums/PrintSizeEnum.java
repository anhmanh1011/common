package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintSizeEnum implements BaseEnum<String> {
    A4("a4", "Mẫu in A4"),
    K80("k80", "Mẫu in K80");

    private String  value;
    private String displayName;

    public static PrintSizeEnum parse(String value) {
        for (PrintSizeEnum type : PrintSizeEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
