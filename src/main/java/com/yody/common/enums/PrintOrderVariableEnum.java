package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintOrderVariableEnum implements BaseEnum<String> {
    ORDER_CODE("{order_code}", "Mã đơn hàng", "SO0001"),
    ORDER_QR_CODE("{order_qr_code}", "Mã đơn hàng dạng QR code", ""),
    BAR_CODE("{bar_code(code)}", "Mã đơn hàng dạng mã vạch", ""),
    MODIFIED_ON("{modified_on}", "Ngày cập nhật", "30-08-2016"),
    MODIFIED_ON_TIME("{modified_on_time}", "Thời gian cập nhật", "15:27:59"),
    SHIP_ON_MIN("{ship_on_min}", "Ngày hẹn giao hàng từ", "30-08-2016"),
    SHIP_ON_MAX("{ship_on_max}", "Thời gian hẹn giao hàng đến", "30-08-2016"),
    ASSIGNEE_NAME("{assignee_name}", "Nhân viên bán hàng", "Nguyễn Thùy Chi"),
    ASSIGNEE_PHONE("{assignee_phone}", "SĐT nhân viên bán hàng", "0912345678"),
    CREATED_ON("{created_on}", "Ngày tạo", "20-08-2016"),
    SOURCE("{source}", "Nguồn", "Pos"),
    ISSUED_ON("{issued_on}", "Ngày chứng từ", "30-08-2016"),
    ACCOUNT_NAME("{account_name}", "Người tạo", "Nguyễn Thùy Chi"),
    ISSUED_ON_TIME("{issued_on_time}", "Thời gian chứng từ", "15:27:59"),
    CREATED_ON_TIME("{created_on_time}", "Thời gian tạo", "15:27:59"),
    REFERENCE("{reference}", "Tham chiếu", "SO00001"),
    PRICE_LIST_NAME("{price_list_name}", "Chính sách giá", "Giá bán buôn"),
    ORDER_STATUS("{order_status}", "Trạng thái đơn hàng", "Hoàn thành"),
    TAG("{tag}", "Thẻ Tag", "Áo thời trang"),
    PAYMENT_STATUS("{payment_status}", "Trạng thái thanh toán", "Thanh toán toàn bộ"),
    EXPECTED_PAYMENT_METHOD("{expected_payment_method}", "Thanh toán dự kiến", "Tiền mặt"),
    CHANNEL("{channel}", "Kênh bán hàng", "Lazada"),
    SHIPPING_FEE_INFORMED_TO_CUSTOMER("{shipping_fee_informed_to_customer}", "Phí ship báo khách",
        "100,000"),
    TOTAL("{total}", "Tổng tiền (giá trị đơn hàng)", "100,000"),
    TOTAL_VAT("{total_vat}", "Tổng giá trị VAT", "10,000"),
    CUSTOMER_CARE_STAFF("{customer_care_staff}", "Nhân viên chăm sóc khách hàng",
        "Nguyễn Thùy Chi"),
    DELIVERY_CODE("{delivery_code}", "Mã vạch hãng vận chuyển", "S1.A1.17373471"),
    DELIVERY_NAME("{delivery_name}", "Hãng vận chuyển", "GHTK"),
    DELIVERY_LOGO("{delivery_logo}", "Logo hãng vận chuyển", ""),
    PACKING_STAFF("{packing_staff}", "Nhân viên đóng gói", "Nguyễn Thùy Chi"),
    BACK_MONEY_TO_CUSTOMER("{back_money_to_customer}", "Tiền trả lại khách", "10,000"),
    MONEY_AFTER_DISCOUNT("{money_after_discount}", "Tổng tiền sau chiết khấu", "90,000"),
    ORDER_MONEY_AFTER_DISCOUNT_BY_TEXT("{order_money_after_discount_by_text}",
        "Tổng tiền sau chiết khấu (bằng chữ)", "Chín mươi nghìn đồng"),
    ORDER_DISCOUNT("{order_discount}", "Tiền chiết khấu đơn hàng", "0"),
    DOWN_PAYMENT("{down_payment}", "Tiền đặt cọc", "10,000"),
    ORDER_WEIGHT("{order_weight}", "Khối lượng đơn hàng", "500g"),
    DISCOUNT_PERCENT("{discount_percent}", "Phần trăm chiết khấu đơn hàng", "10%"),
    REFERENCES_SEE("{references_see}", "Có cho khách xem hàng", "Cho khách xem hàng"),
    TYPE_SHIPPING_SERVICE("{type_shipping_service}", "Loại dịch vụ vận chuyển", "Tự giao hàng"),
    COUPON_CODE("{coupon_code}", "Mã Coupon", "CP1234"),
    FEE_SUPPORT("{fee_support}",
        "Hỗ trợ cước (Phí vận chuyển + Phí thu tiền hộ - Phí ship báo khách)", "10,000"),
    FEE_SUPPORT_THREE_PLS("{fee_support_three_pls}",
        "Cước trả HVC (Phí vận chuyển + Phí thu tiền hộ)", "20,000"),
    ORDER_NOTE("{order_note}", "Ghi chú đơn hàng", "Khách hàng đặt hàng qua Facebook"),
    ORDER_NOTE_CARE_STAFF("{order_note_care_staff}", "Ghi chú của chăm sóc khách hàng",
        "Khách hàng thích đồ màu đỏ"),
    ZIP_CODE("{zip_code}", "Mã bưu cục", "10000"),
    SHIPPING_NOTE("{shipping_note}", "Ghi chú vận chuyển", "Ship gấp");

    private String value;
    private String displayName;
    private String previewValue;

    public static PrintOrderVariableEnum parse(String value) {
        for (PrintOrderVariableEnum type : PrintOrderVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
