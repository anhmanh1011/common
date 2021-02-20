package com.yody.common.utility;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excels {

  public static <T> List<T> fromExcel(Class<T> type, String filePath, Integer skip) throws Exception {
    List<T> lstObj = Collections.emptyList();
    PoijiOptions options = null;
    if (skip == null || skip <= 0) {
      options = PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
    } else {
      options = PoijiOptions.PoijiOptionsBuilder.settings(skip).addListDelimiter(";").build();
    }
    File file = new File(filePath);
    if (file.exists()) {
      lstObj = Poiji.fromExcel(file, type, options);
    } else {
      throw new Exception("File not found!");
    }

    return lstObj;
  }

  public static <T> List<T> fromExcel(Class<T> type, String filePath) throws Exception {
    return fromExcel(type, filePath, null);
  }

  @SuppressWarnings("resource")
  public static <T> void genExcel(List<T> lstObj, Class<T> type, String excelPath, String sheetName,
      List<String> lstHeader, int rowStart, int cellStart) throws Exception {
    Field[] fields = type.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
    }
    int rowIndex = rowStart;
    int cellIndex = cellStart;

    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet(sheetName);
    XSSFFont font = workbook.createFont();
    CellStyle style = workbook.createCellStyle();
    style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);

    Row row = sheet.createRow(rowIndex++);
    for (String header : lstHeader) {
      Cell cell = row.createCell(cellIndex++);
      cell.setCellStyle(style);
      cell.setCellValue(header);
    }

    font.setBold(false);
    for (T obj : lstObj) {
      row = sheet.createRow(rowIndex++);
      cellIndex = cellStart;
      for (Field field : fields) {
        Object fieldValue = field.get(obj);
        if (fieldValue == null) {
          cellIndex++;
          continue;
        }
        Cell cell = row.createCell(cellIndex++);
        cell.setCellValue("" + fieldValue);
      }
    }

    try (FileOutputStream fileOut = new FileOutputStream(excelPath)) {
      workbook.write(fileOut);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
