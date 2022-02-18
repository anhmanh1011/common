package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BankAccountEnum implements BaseEnum<String> {
  VIETIN_BANK("VietinBank", "Ngân hàng TMCP Á Châu ACB"),
  TP_BANK("TPBank", "Ngân hàng TMCP Tiên Phong"),
  DAB("DAB", "Ngân hàng TMCP Đông Á"),
  SEA_BANK("SeABank", "Ngân Hàng TMCP Đông Nam Á"),
  AB_BANK("ABBANK", "Ngân hàng TMCP An Bình"),
  BACA_BANK("BacABank", "Ngân hàng TMCP Bắc Á"),
  VIET_CAPITAL_BANK("VietCapitalBank", "Ngân hàng TMCP Bản Việt"),
  MSB("MSB", "Ngân hàng TMCP Hàng hải Việt Nam"),
  TCB("TCB", "Ngân hàng TMCP Kỹ Thương Việt Nam"),
  KIENLONG_BANK("KienLongBank", "Ngân hàng TMCP Kiên Long"),
  NAMA_BANK("Nam A Bank", "Ngân hàng TMCP Nam Á"),
  NCB("NCB", "Ngân hàng TMCP Quốc Dân"),
  VP_BANK("VPBank", "Ngân hàng TMCP Việt Nam Thịnh Vượng"),
  HD_BANK("HDBank", "Ngân hàng TMCP Phát triển Thành phố Hồ Chí Minh"),
  OCB("OCB", "Ngân hàng TMCP Phương Đông"),
  MB("MB", "Ngân hàng TMCP Quân đội"),
  PV_COMBANK("PVcombank", "Ngân hàng TMCP Đại chúng"),
  VIB("VIB", "Ngân hàng TMCP Quốc tế Việt Nam"),
  SCB("SCB", "Ngân hàng TMCP Sài Gòn"),
  SGB("SGB", "Ngân hàng TMCP Sài Gòn Công Thương"),
  SHB("SHB", "Ngân hàng TMCP Sài Gòn – Hà Nội"),
  STB("STB", "Ngân hàng TMCP Sài Gòn Thương Tín"),
  VAB("VAB", "Ngân hàng TMCP Việt Á"),
  BVB("BVB", "Ngân hàng TMCP Bảo Việt"),
  VIET_BANK("VietBank", "Ngân hàng TMCP Việt Nam Thương Tín"),
  PG_BANK("PG Bank", "Ngân Hàng TMCP Xăng Dầu Petrolimex"),
  EIB("EIB", "Ngân Hàng TMCP Xuất Nhập khẩu Việt Nam"),
  LPB("LPB", "Ngân Hàng TMCP Bưu điện Liên Việt"),
  VCB("VCB", "Ngân Hàng TMCP Ngoại thương Việt Nam"),
  CTG("CTG", "Ngân Hàng TMCP Công Thương Việt Nam"),
  BID("BID", "Ngân Hàng TMCP Đầu tư và Phát triển Việt Nam");
  private final String value;
  private final String displayName;

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public String getDisplayName() {
    return this.displayName;
  }

  public static BankAccountEnum parse(String value) {
    for (BankAccountEnum type : BankAccountEnum.values()) {
      if (type.getValue().equals(value)) {
        return type;
      }
    }
    return null;
  }
}
