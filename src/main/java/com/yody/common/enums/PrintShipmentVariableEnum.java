package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintShipmentVariableEnum implements BasePrintEnum<String> {
    SENDER_FULL_NAME("{sender_full_name}", "Tên người gửi", "Trịnh Thị Tí"),
    SENDER_PHONE("{sender_phone}", "Số điện thoại người gửi", "0933445566"),
    SENDER_ADDRESS("{sender_address}", "Địa chỉ người gửi", "Hải Dương"),
    SENDER_EMAIL("{sender_email}", "Email người gửi", "tinatasha@gmail.com"),
    SHIPPING_FULL_NAME("{shipping_full_name}", "Tên người nhận", "Lê Văn Cáp"),
    SHIPPING_PHONE("{shipping_phone}", "Số điện thoại người nhận", "0988334881"),
    SHIPPING_SECOND_PHONE("{shipping_second_phone}", "Thông tin người nhận 2", "Vợ - 0988334881"),
    SHIPPING_ADDRESS("{shipping_address}", "Địa chỉ người nhận", "Hà Nội"),
    SHIPPING_EMAIL("{shipping_email}", "Email người nhận", "steverogers@gmail.com"),
    SHIPPING_FULL_ADDRESS("{shipping_full_address}",
        "Phường xã, quận huyện, tỉnh thành người nhận",
        "76 Triều Khúc - Tân Triều - Thanh Trì - Hà Nội"),
    SHIPPING_PROVINCE("{shipping_province}", "Tỉnh/thành phố người nhận", "Hà Nội"),
    SHIPPING_DISTRICT("{shipping_district}", "Quận huyện người nhận", "Thanh Trì"),
    SHIPPING_WARD("{shipping_ward}", "Phường xã người nhận", "Tân Triều"),
    CUSTOMER_POINT("{customer_point}", "Điểm tích lũy của khách hàng", "1000"),
    FANPAGE_NAME("{fanpage_name}", "Tên fanpage", "https://www.facebook.com/yody.vn");

    private String value;
    private String displayName;
    private String previewValue;

    public static PrintShipmentVariableEnum parse(String value) {
        for (PrintShipmentVariableEnum type : PrintShipmentVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
