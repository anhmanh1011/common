package common.utility;

import com.yody.common.utility.Excels;
import common.utility.entity.Employee;
import common.utility.entity.Imex;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExcelsTest {

  private final String RESOURCE_PATH = "\\src\\test\\resources";
  private final String FILE_NAME = "employees.xlsx";

  @Test
  public void fromExcelTest() throws Exception {
    String filePath = System.getProperty("user.dir") + RESOURCE_PATH + File.separator + FILE_NAME;
    List<Employee> employees;
    employees = Excels.fromExcel(Employee.class, filePath, 1);
    employees.forEach((emp) -> {
      System.out.println(emp);
    });
  }

  @Test
  public void fromExcel2Test() throws Exception {
    String filePath = System.getProperty("user.dir") + RESOURCE_PATH + File.separator + FILE_NAME;
    List<Employee> employees = Excels.fromExcel(Employee.class, filePath);
    employees.forEach((emp) -> {
      System.out.println(emp);
    });
  }

  @Test
  public void genExcelTest() throws Exception {
    String destPath = "D:\\Import_Imex.xlsx";
    String sheetName = "XNK";

    List<Imex> lstImex = new ArrayList<>();
    lstImex.add(new Imex("APN2021-D-XL", "K3-D5", "Chiếc", "100", "200000", "20%", "Đồng Giá Lụa Nến", null, null));
    lstImex.add(new Imex("Bộ nam hoodie logo - Đen - XL", "BTM3475-DEN-XL", "Chiếc", "100", "759.000", "379.500",
        "SALE 50% SP THU ĐÔNG", null, null));
    lstImex.add(new Imex("Túi vải YODY mới (Loại nhỏ)", "ZTM2", "Chiếc", "100", "399.000", "130.000",
        "Đồng Giá Lụa Nến", "29/01/2021", null));

    List<String> lstHeader = new ArrayList<String>();
    lstHeader.add("Sản phẩm");
    lstHeader.add("Lô hàng");
    lstHeader.add("Đơn vị tính");
    lstHeader.add("Số lượng");
    lstHeader.add("Giá");
    lstHeader.add("Chiết khấu");
    lstHeader.add("Ghi chú");
    lstHeader.add("Ngày hết hạn");
    lstHeader.add("Cảnh báo trước");

    Excels.genExcel(lstImex, Imex.class, destPath, sheetName, lstHeader, 1, 1);
  }
}
