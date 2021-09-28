package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StockSyncStatusEnum implements BaseEnum<String> {
    DONE("done", "Thành công"),
    IN_PROGRESS("in_progress", "Đang xử lý"),
    ERROR("error", "Không thành công");
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

    public static StockSyncStatusEnum parse(String value) {
        for (StockSyncStatusEnum type : StockSyncStatusEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
