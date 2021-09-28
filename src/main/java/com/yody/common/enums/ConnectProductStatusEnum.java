package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ConnectProductStatusEnum implements BaseEnum<String> {
    CONNECTED("connected", "Thành công"),
    WAITING("waiting", "Chưa ghép nối");
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

    public static ConnectProductStatusEnum parse(String value) {
        for (ConnectProductStatusEnum type : ConnectProductStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
