package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintProductReturnVariableEnum implements BasePrintEnum<String> {
    VARIANT_CODE("{variant_code_or}", "Mã sản phẩm đổi trả", "APM3681"),
    SKU("{sku_return_or}", "Sku đổi trả", "TLN1111"),
    PRODUCT_NAME("{product_name_or}", "Tên sản phẩm đổi trả", "Áo Polo Nam Coolmax Phối Bo Kẻ"),
    PRODUCT_BRAND("{product_brand_or}", "Thương hiệu sản phẩm đổi trả", "Coolmax"),
    PRODUCT_QUANTITY("{product_quantity_or}", "Số lượng sản phẩm đổi trả", "2"),
    GIFT("{gift_or}", "Quà tặng sản phẩm đổi trả", "Tất lưới"),
    VARIANT_BARCODE("{variant_barcode_or}", "Mã vạch sản phẩm đổi trả",
        "<img src=\"https://cdn.yody.io/product_barcode.png\" width=\"100\" height=\"50\">"),
    PRODUCT_WEIGHT("{product_weight_or}", "Khối lượng sản phẩm đổi trả (gram)", "200g"),
    PRODUCT_PRICE_INCLUDED_VAT("{product_price_included_vat_or}", "Giá sản phẩm (đã bao gồm VAT)",
        "110,000"),
    PRODUCT_PRICE_NOT_INCLUDED_VAT("{product_price_not_included_vat_or}",
        "Giá sản phẩm đổi trả (chưa bao gồm VAT)", "100,000"),
    PRODUCT_PRICE_AFTER_DISCOUNT("{product_price_after_discount_or}", "Giá sản phẩm đổi trả (sau Chiết khấu)",
        "55,000"),
    TOTAL_MONEY_BEFORE_DISCOUNT("{total_money_before_discount_or}", "Tổng tiền sp đổi trả trước chiết khấu",
        "220,000"),
    TOTAL_MONEY_AFTER_DISCOUNT("{total_money_after_discount_or}", "Tổng tiền sp đổi trả sau chiết khấu",
        "110,000"),
    PRODUCT_DISCOUNT_MONEY("{product_discount_money_or}", "Tiền chiết khấu sản phẩm đổi trả", "110,000"),
    PRODUCT_DISCOUNT_PERCENT("{product_discount_percent_or}", "% chiết khấu sản phẩm đổi trả", "50%"),
    PRODUCT_VAT("{product_vat_or}", "VAT sản phẩm đổi trả", ""),
    PRODUCT_UNIT("{product_unit_or}", "Đơn vị sản phẩm đổi trả", "Chiếc"),
    PRODUCT_IMAGE("{product_image_or}", "Ảnh sản phẩm đổi trả", ""),
    MADE_IN("{made_in_or}", "Xuất xứ sản phẩm đổi trả", "Việt Nam"),
    PRODUCT_NOTE("{product_note_or}", "Ghi chú sản phẩm đổi trả",
        "Bảo quản sản phẩm nơi thông thoáng, khô ráo."),
    PRODUCT_CATEGORY("{product_category_or}", "Mã danh mục sản phẩm đổi trả", "APM"),
    PRODUCT_INDEX("{product_index_or}", "Số thứ tự đổi trả", "1");

    private String value;
    private String displayName;
    private String previewValue;

    public static PrintProductReturnVariableEnum parse(String value) {
        for (PrintProductReturnVariableEnum type : PrintProductReturnVariableEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
