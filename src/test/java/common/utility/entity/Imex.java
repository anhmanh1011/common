package common.utility.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Imex {

  private String product;

  private String batch;

  private String unit;

  private String quantity;

  private String price;

  private String discount;

  private String note;

  private String expiredDate;

  private String alert;

}
