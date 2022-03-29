package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrintOrderReturnVariableEnum implements BasePrintEnum<String> {
  ORDER_CODE("{order_return_code}", "Mã đơn trả hàng", "SRNO0001"),
  PAID_AMOUNT("{paid_amount}", "Tổng tiền trả", "100,000"),
  POINT_REFUND("{point_refund}", "Điểm hoàn", "10"),
  REASON("{reason}", "Lý do trả hàng", "Đổi trả hàng"),
  SUB_REASON("{sub_reason}", "Lý do chi tiết trả hàng", "Đổi màu "),
  RETURN_DATE("{return_date}", "Ngày trả", "30-08-2016"),
  RETURN_TIME("{RETURN_TIME}", "Thời gian trả", "15:27:59"),
  RECEIVE_DATE("{receive_date}", "Ngày nhận", "30-08-2016"),
  RECEIVE_TIME("{receive_time}", "Thời gian nhận", "15:27:59"),
  ACCOUNT("{account}", "Nhân viên trả hàng", "15:27:59"),
  ACCOUNT_CODE("{account_code}", "Mã nhân viên trả hàng", "15:27:59"),
  BACK_MONEY_TO_CUSTOMER("{back_money_to_customer}", "Tiền trả khách", "100,000"),
  BACK_MONEY_TO_CUSTOMER_TEXT("{back_money_to_customer_text}", "Tiền trả khách bằng chữ", "Một trăm nghìn đồng"),
  CONTENT("{content}", "Nội dung thanh toán tiền", "Khách cần trả"),
  ;

  private final String value;
  private final String displayName;
  private final String previewValue;

  public static PrintOrderReturnVariableEnum parse(String value) {
    for (PrintOrderReturnVariableEnum type : PrintOrderReturnVariableEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
