package common.utility.entity;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ExcelSheet("Sheet1")
public class Employee {

  @ExcelCellName("ID")
  private long employeeId;

  @ExcelCellName("NAME")
  private String name = "";

  @ExcelCellName("SURNAME")
  private String surname;

  @ExcelCellName("AGE")
  private Integer age;

  @ExcelCellName("SINGLE")
  private boolean single;

  @ExcelCellName("BIRTHDAY")
  private String birthday;

}
