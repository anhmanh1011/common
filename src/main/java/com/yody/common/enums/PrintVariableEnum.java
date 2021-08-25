package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintVariableEnum implements BaseEnum<String> {
    COMPANY_NAME("{company_name}", "Tên công ty"),
    STORE_NAME("{store_name}", "Tên cửa hàng"),
    STORE_ADDRESS("{store_address}", "Địa chỉ cửa hàng"),
    STORE_PHONE("{store_phone}", "SĐT cửa hàng"),
    ORDER_CODE("{order_code}", "Mã đơn hàng"),
    BAR_CODE("{bar_code}", "Mã đơn hàng dạng mã vạch"),
    MODIFIED_ON("{modified_on}", "Ngày cập nhật"),
    MODIFIED_ON_TIME("{modified_on_time}", "Thời gian cập nhật"),
    ASSIGNEE_NAME("{assignee_name}", "Người phụ trách"),
    SOURCE("{source}", "Nguồn"),
    ACCOUNT_NAME("{account_name}", "Người tạo"),
    CREATED_ON("{created_on}", "Ngày tạo"),
    CREATED_ON_TIME("{created_on_time}", "Thời gian tạo"),
    REFERENCE("{reference}", "Tham chiếu"),
    PRICE_LIST_NAME("{price_list_name}", "Chính sách giá"),
    ORDER_STATUS("{order_status}", "Trạng thái đơn hàng"),
    TAG("{tag}", "Thẻ Tag"),
    PAYMENT_STATUS("{payment_status}", "Trạng thái thanh toán"),
    CHANNEL("{channel}", "Kênh bán hàng"),
    SHIPPING_FEE_INFORMED_TO_CUSTOMER("{shipping_fee_informed_to_customer}", "Phí giao hàng báo khách"),
    TOTAL("{total}", "Tổng tiền hàng"),
    PRODUCT_CODE("{product_code}", "Mã sản phẩm"),
    PRODUCT_NAME("{product_name}", "Tên sản phẩm"),
    PRODUCT_BRAND("{product_brand}", "Thương hiệu"),
    PRODUCT_QUANTITY("{product_quantity}", "Số lượng"),
    GIFT("{gift}", "Quà tặng"),
    PRODUCT_BARCODE("{product_barcode}", "Mã sản phẩm dạng mã vạch"),
    PRODUCT_WEIGHT("{product_weight}", "Tổng khối lượng sản phẩm"),
    PRODUCT_PRICE_INCLUDED_VAT("{product_price_included_vat}", "Giá sản phẩm bao gồm VAT"),
    PRODUCT_PRICE_NOT_INCLUDED_VAT("{product_price_not_included_vat}", "Giá sản phẩm chưa bao gồm VAT"),
    PRODUCT_PRICE_AFTER_DISCOUNT("{product_price_after_discount}", "Giá sau chiết khấu trên 1 sản phẩm"),
    TOTAL_MONEY_BEFORE_DISCOUNT("{total_money_before_discount}", "Tổng tiền trước chiết khấu"),
    TOTAL_MONEY_AFTER_DISCOUNT("{total_money_after_discount}", "Tổng tiền sau chiết khấu"),
    PRODUCT_DISCOUNT_MONEY("{product_discount_money}", "Số tiền chiết khấu"),
    PRODUCT_DISCOUNT_PERCENT("{product_discount_percent}", "Chiết khấu theo %"),

    SHIPPING_FULL_NAME("{shipping_full_name}", "Người nhận"),
    SHIPPING_PHONE("{shipping_phone}", "SĐT người nhận"),
    SHIPPING_ADDRESS("{shipping_address}", "Địa chỉ nhận"),
    SHIPPING_EMAIL("{shipping_email}", "Email người nhận"),
    SHIPPING_FULL_ADDRESS("{shipping_full_address}", "Địa chỉ nhận hàng đầy đủ"),
    SHIPPING_PROVINCE("{shipping_province}", "Tỉnh/Thành phố"),
    SHIPPING_DISTRICT("{shipping_district}", "Quận/Huyện"),
    SHIPPING_WARD("{shipping_ward}", "Phường/Xã");

    private String value;
    private String displayName;

    public static PrintVariableEnum parse(String value) {
        for (PrintVariableEnum type : PrintVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
