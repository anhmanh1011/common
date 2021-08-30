package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintStoreVariableEnum implements BasePrintEnum<String> {
    COMPANY_LOGO("{company_logo}", "Logo doanh nghiệp", "<img src=\"https://storage.googleapis.com/cdn.nhanh.vn/store/3138/logo_1615426885_logo-yody.png\" width=\"150\" height=\"70\">"),
    COMPANY_NAME("{company_name}", "Tên doanh nghiệp", "Công ty cổ phần thời trang YODY"),
    COMPANY_ADDRESS("{company_address}", "Địa chỉ doanh nghiệp",
        "An Định, Việt Hoà, Thành phố Hải Dương, Hải Dương 03125"),
    COMPANY_PHONE("{company_phone}", "Số điện thoại doanh nghiệp", "1800 2086"),
    STORE_LOGO("{store_logo}", "Logo cửa hàng", "<img src=\"https://img.jamja.vn/jamja-prod/gcs_full_58ab0f6b76ec57406acc4f15-2017-02-20-154652.png\" width=\"150\" height=\"70\">\""),
    STORE_NAME("{store_name}", "Tên cửa hàng", "Cửa hàng thời trang YODY"),
    STORE_ADDRESS("{store_address}", "Địa chỉ cửa hàng",
        "61 Trần Hưng Đạo, P. Trần Hưng Đạo, Thành phố Hải Dương, Hải Dương 170000"),
    STORE_PHONE("{store_phone}", "Số điện thoại cửa hàng", "091 415 22 58"),
    LOCATION_NAME("{location_name}", "Tên chi nhánh", "YODY Hong Quang"),
    LOCATION_ADDRESS("{location_address}", "Địa chỉ chi nhánh",
        "45 Hồng Quang, P. Nguyễn Trãi, Thành phố Hải Dương, Hải Dương 170000"),
    LOCATION_PHONE("{location_phone}", "Số điện thoại chi nhánh", "091 426 82 58");

    private String value;
    private String displayName;
    private String previewValue;

    public static PrintStoreVariableEnum parse(String value) {
        for (PrintStoreVariableEnum type : PrintStoreVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
