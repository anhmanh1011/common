package com.yody.common.enums;

import com.yody.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StockTransferHistoryTypeEnum implements BaseEnum<String> {
  CREATE("create", "Tạo mới"),
  UPDATE("update", "Cập nhật thông tin chuyển kho"),
  DELETE("delete", "Xóa đơn chuyển kho"),
  CREATE_SHIPMENT("create_shipment", "Tạo mới đơn vận chuyển"),
  CANCEL_SHIPMENT("cancel_shipment", "Hủy đơn vận chuyển"),
  RECEIVE("receive", "Nhận hàng"),
  CREATE_PROGRESS_EXCEPTION("create_progress_exception", "Tạo xử lý thừa/thiếu"),
  CANCEL_PROGRESS_EXCEPTION("cancel_progress_exception", "Hủy xử lý thừa/thiếu"),
  CONFIRM_EXCEPTION("confirm_exception", "Xác nhận thừa thiếu đơn hàng");
  private String value;
  private String displayName;

  public static StockTransferHistoryTypeEnum parse(String value) {
    for (StockTransferHistoryTypeEnum v : StockTransferHistoryTypeEnum.values()) {
      if (v.getValue().equals(value)) {
        return v;
      }
    }
    return null;
  }
}
