package com.yody.common.enums;

import com.yody.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum StockTransferStatusEnum implements BaseEnum<String> {
    ACTIVE("active", "Chờ chuyển"),
    TRANSFERRING("transferring", "Đang chuyển"),
    PENDING("pending", "Chờ xử lý"),
    CANCELED("canceled", "Đã hủy"),
    COMPLETED("completed", "Đã nhập");
    private String value;
    private String displayName;

    public static StockTransferStatusEnum parse(String value) {
        for (StockTransferStatusEnum v : StockTransferStatusEnum.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
