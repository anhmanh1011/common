package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintPurchaseOrderEnum implements BasePrintEnum<String> {
  PURCHASE_ORDER_ID("{purchase_order_id}", "Mã đơn hàng", "1"),
  PURCHASE_ORDER_CODE("{purchase_order_code}", "Mã đơn hàng", "PO000000001"),
  PO_MERCHANDISER_NAME("{po_merchandiser_name}", "Merchandiser", "Nguyễn Bảo Quỳnh"),
  PO_DESIGNER_NAME("{po_designer_name}", "Nhà thiết kế", "Nguyễn Bảo Quỳnh"),
  MERCHANDISER_PHONE_NUMBERS("{merchandiser_phone_numbers}", "SĐT Merchandiser", "G096123456"),
  SUPPLIER_NAME("{supplier_name}", "Tên nhà cung cấp", "Quần áo Yody"),
  SUPPLIER_ADDRESS("{supplier_address}", "Địa chỉ nhà cung cấp", "An Định"),
  SUPPLIER_KEYCONTACT_NAME("{supplier_keycontact_name}", "Đại diện", "Nguyễn Dương"),
  SUPPLIER_KEYCONTACT_POSITION("{supplier_keycontact_position}", "Chức vụ", "Người đại diện"),
  SUPPLIER_PHONE_NUMBER("{supplier_phone_number}", "Điện thoại", "G096123456"),
  SUPPLIER_DEBT_TIME("{supplier_debt_time}", "Thanh toán sau số ngày", "45"),
  SUPPLIER_DEBT_TIME_UNIT_NAME("{supplier_debt_time_unit_name}", "Thời gian thanh toán sau", "Ngày"),
  BANK_ACCOUNT_NUMBERS("{bank_account_numbers}", "TK ngân hàng", "123456789"),
  BANK_ACCOUNT_NAME("{bank_account_name}", "Tên TK", "123456789"),
  BANK_NAME("{bank_name}", "Ngân hàng", "Techcombank"),
  TAX_CODE("{tax_code}", "Mã số thuế", "123456789"),
  PRODUCT_VARIANT_SKU("{product_variant_sku}", "Mã sản phẩm", "APN1234"),
  PO_PRODUCT_VARIANT_IMAGE("{po_product_variant_image}", "Hình sản phẩm", "https://yody-file.s3.ap-southeast-1.amazonaws.com/variant/2021-10-01-14-56-07_151803f7-82c8-4cfb-8120-3e3af8174a70.png"),
  PO_RETAIL_PRICE("{po_retail_price}", "Giá bán", "2000"),
  PO_ORDERLINE_QUANTITY("{po_orderline_quantity}", "Số lượng nhập hàng", "30"),
  PO_TOTAL_QUANTITIES("{po_total_quantities}", "Tổng số lượng nhập hàng", "30"),
  PO_UNIT_PRICE("{po_unit_price}", "Đơn giá nhập", "VNĐ"),
  PO_ORDERLINE_PRICE("{po_orderline_price}", "Thành tiền", "60000"),
  PO_VAT_RATE("{po_vat_rate}", "Thuế VAT", "6000"),
  PO_VAT_AMOUNT("{po_vat_amount}", "Gíá trị VAT", "10%"),
  PO_VAT_TOTAL_AMOUNT("{po_vat_total_amount}", "Tổng VAT", "Tổng VAT"),
  PO_TOTAL_AMOUNT("{po_total_amount}", "Tổng tiền", "Ghi chú nội bộ  "),
  PO_SUPPLIER_NOTE("{po_supplier_note}", "Ghi chú Nhà cung cấp", "Ghi chú Nhà cung cấp"),
  PO_INTERNAL_NOTE("{po_internal_note}", "Ghi chú nội bộ", "Ghi chú nội bộ  "),
  PO_PROCUREMENT_PLAN_ARRAY("{po_procurement_plan_array}", "Bảng kế hoạch nhập kho", ""),
  PO_PAYMENT_TERM("{po_payment_term}", "Điều khoản thanh toán", ""),
  PO_PAYMENT_NOTE("{po_payment_note}", "Diễn giải thanh toán", ""),
  PO_PAYMENT_PLAN_ARRAY("{po_payment_plan_array}", "Bảng kế hoạch thanh toán", ""),
  PO_REFERENCE("{po_reference}", "Mã tham chiếu", "D00000001");

  private final String value;
  private final String displayName;
  private final String previewValue;

  public static PrintProductVariableEnum parse(String value) {
    for (PrintProductVariableEnum type : PrintProductVariableEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
