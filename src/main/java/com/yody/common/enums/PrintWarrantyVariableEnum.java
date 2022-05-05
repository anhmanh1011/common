package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintWarrantyVariableEnum implements BasePrintEnum<String> {
    APPOINTMENT_DATE("{appointment_date}", "Ngày hẹn trả", "21/04/22 10:11"),
    REASON_WARRANTY("{reason_warranty}", "Lý do bảo hành", "May lại đường chỉ"),
    WARRANTY_INDEX("warranty_index", "Số lượng", "1"),
    PRODUCT_WARRANTY("product_warranty", "Sản phẩm", "áo Yody"),
    PRODUCT_PRICE_WARRANTY("product_price_warranty", "Giá", "100.000"),
    BH_STORE_NAME("{bh_store_name}", "Tên cửa hàng", "YODY Test"),
    BH_STORE_ADDRESS("{bh_store_address}", "Địa chỉ cửa hàng", "90 nguyễn tuân"),
    BH_ASSIGNEE_NAME("{bh_assignee_name}", "Họ tên", "YODY IT"),
    BH_APPOINTMENT_DATE("{bh_appointment_date}", "Ngày hẹn trả", "06/05/2022"),
    ;

    private final String value;
    private final String displayName;
    private final String previewValue;

    public static PrintWarrantyVariableEnum parse(String value) {
        for (PrintWarrantyVariableEnum type : PrintWarrantyVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
