package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintProductVariableEnum implements BasePrintEnum<String> {
    PRODUCT_CODE("{product_code}", "Mã sản phẩm", "APM3681"),
    PRODUCT_NAME("{product_name}", "Tên sản phẩm", "Áo Polo Nam Coolmax Phối Bo Kẻ"),
    PRODUCT_BRAND("{product_brand}", "Thương hiệu sản phẩm", "Coolmax"),
    PRODUCT_QUANTITY("{product_quantity}", "Số lượng sản phẩm", "2"),
    GIFT("{gift}", "Quà tặng sản phẩm", "Tất lưới"),
    PRODUCT_BARCODE("{product_barcode}", "Mã vạch sản phẩm", ""),
    PRODUCT_WEIGHT("{product_weight}", "Khối lượng sản phẩm (gram)", "200g"),
    PRODUCT_PRICE_INCLUDED_VAT("{product_price_included_vat}", "Giá sản phẩm (đã bao gồm VAT)",
        "110,000"),
    PRODUCT_PRICE_NOT_INCLUDED_VAT("{product_price_not_included_vat}",
        "Giá sản phẩm (chưa bao gồm VAT)", "100,000"),
    PRODUCT_PRICE_AFTER_DISCOUNT("{product_price_after_discount}", "Giá sản phẩm (sau Chiết khấu)",
        "55,000"),
    TOTAL_MONEY_BEFORE_DISCOUNT("{total_money_before_discount}", "Tổng tiền sp trước chiết khấu",
        "220,000"),
    TOTAL_MONEY_AFTER_DISCOUNT("{total_money_after_discount}", "Tổng tiền sp sau chiết khấu",
        "110,000"),
    PRODUCT_DISCOUNT_MONEY("{product_discount_money}", "Tiền chiết khấu sản phẩm", "110,000"),
    PRODUCT_DISCOUNT_PERCENT("{product_discount_percent}", "% chiết khấu sản phẩm", "50%"),
    PRODUCT_VAT("{product_vat}", "VAT sản phẩm", ""),
    PRODUCT_UNIT("{product_unit}", "Đơn vị sản phẩm", "Chiếc"),
    PRODUCT_IMAGE("{product_image}", "Ảnh sản phẩm", ""),
    MADE_IN("{made_in}", "Xuất xứ sản phẩm", "Việt Nam"),
    PRODUCT_NOTE("{product_note}", "Ghi chú sản phẩm",
        "Bảo quản sản phẩm nơi thông thoáng, khô ráo."),
    PRODUCT_CATEGORY("{product_category}", "Mã danh mục sản phẩm", "APM");

    private String value;
    private String displayName;
    private String previewValue;

    public static PrintProductVariableEnum parse(String value) {
        for (PrintProductVariableEnum type : PrintProductVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
