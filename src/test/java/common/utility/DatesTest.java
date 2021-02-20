package common.utility;

import com.yody.common.enums.DateTimeEnum;
import com.yody.common.utility.Dates;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DatesTest {

  @Test
  public void isBetweenRangeTest() throws Exception {
    Date current = new Date();
    Random ramdom = new Random();
    int max = 10;
    int min = 0;
    int value = ramdom.nextInt((max - min) + 1) + min;
    Date startDate = Dates.addDay(current, value);
    Date endDate = Dates.addDay(current, -value);
    Assert.assertTrue(Dates.isBetweenRange(current, startDate, endDate));
  }

  @Test
  public void validateFormatTest() throws Exception {
    String input = "11-01-2021 15:56:50";
    Assert.assertTrue(Dates.validateFormat(input, DateTimeEnum.ddMMyyyHHmmss.getValue()));
  }

  @Test
  public void isSameDayTest() throws Exception {
    Calendar cal1 = Calendar.getInstance();
    cal1.set(2021, 0, 9);
    Calendar cal2 = Calendar.getInstance();
    cal2.set(2021, 0, 9, 16, 9, 33);
    Assert.assertTrue(Dates.isSameDay(cal1, cal2));
  }

  @Test
  public void isSameDayDateTest() throws Exception {
    Date date1 = new SimpleDateFormat(DateTimeEnum.dd_MM_yyyy.getValue()).parse("11-01-2021");
    Date date2 = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-01-2021 15:56:50");
    Assert.assertTrue(Dates.isSameDay(date1, date2));
  }

  @Test
  public void isTodayTest() throws Exception {
    Date date = new Date();
    Assert.assertTrue(Dates.isToday(date));
  }

  @Test
  public void isTodayCalendarTest() throws Exception {
    Calendar cal = Calendar.getInstance();
    Assert.assertTrue(Dates.isToday(cal));
  }

  @Test
  public void getDayOfThisMonthTest() throws Exception {
    Assert.assertNotNull(Dates.getDayOfThisMonth(23));
  }

  @Test
  public void changeFormatTest() throws Exception {
    String input = "11-01-2021 15:56:50";
    Date dateInput = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse(input);
    String output = Dates
        .changeFormat(input, DateTimeEnum.ddMMyyyHHmmss.getValue(), DateTimeEnum.dd_MM_yyyy.getValue());
    Date dateOutput = new SimpleDateFormat(DateTimeEnum.dd_MM_yyyy.getValue()).parse(output);
    Assert.assertNotNull(Dates.isSameDay(dateInput, dateOutput));
  }

  @Test
  public void toDateTest() throws Exception {
    String date = "11-01-2021 15:56:50";
    Date output = Dates.toDate(date, DateTimeEnum.ddMMyyyHHmmss.getValue());
    System.out.println(output);
    Assert.assertNotNull(output);
  }

  @Test
  public void toStringTest() throws Exception {
    Date date = new Date();
    String output = Dates.toString(date, DateTimeEnum.ddMMyyyHHmmss.getValue());
    System.out.println(output);
    Assert.assertNotNull(output);
  }

  @Test
  public void addDayTest() throws Exception {
    Date date = new Date();
    Date result = Dates.addDay(date, -100);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void addSecondTest() throws Exception {
    Date date = new Date();
    Date result = Dates.addSecond(date, 60);
    System.out.println(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void addMiliSecondTest() throws Exception {
    Date date = new Date();
    Date result = Dates.addMiliSecond(date, 1000);
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(date));
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(result));
    Assert.assertNotNull(result);
  }

  @Test
  public void addHourTest() throws Exception {
    Date date = new Date();
    Date result = Dates.addHour(date, 5);
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(date));
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(result));
    Assert.assertNotNull(result);
  }

  @Test
  public void addMonthTest() throws Exception {
    Date date = new Date();
    Date result = Dates.addMonth(date, 5);
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(date));
    System.out.println(new SimpleDateFormat(DateTimeEnum.yyyyMMdd_T_HHmmssSSSXXX.getValue()).format(result));
    Assert.assertNotNull(result);
  }

  @Test
  public void isValidDateTest() throws Exception {
//		String strDate = "11:01-2021 15:56:50";
    String strDate = "11-01-2021 15:56:50";
    Assert.assertTrue(Dates.isValidDate(strDate));
  }

  @Test
  public void isValidFormatTest() throws Exception {
//		String strDate = "11-01-2021 15:56:50";
    String strDate = "2020-01-13";
    Assert.assertTrue(Dates.isValidFormat(strDate));
  }

  @Test
  public void todayTest() throws Exception {
    String strDate = Dates.today(DateTimeEnum.ddMMyyyHHmmss.getValue());
    System.out.println(strDate);
    Assert.assertNotNull(strDate);
  }

  @Test
  public void getDayOfMonthTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2021 15:56:50");
    Integer result = Dates.getDayOfMonth(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getLastDayOfMonthTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2021 15:56:50");
    Integer result = Dates.getLastDayOfMonth(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getMonthTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2021 15:56:50");
    Integer result = Dates.getMonth(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getCurrentMonthTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2021 15:56:50");
    Integer result = Dates.getCurrentMonth(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getYearTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2029 15:56:50");
    int result = Dates.getYear(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void toTimestampTest() throws Exception {
    Date date = new SimpleDateFormat(DateTimeEnum.ddMMyyyHHmmss.getValue()).parse("11-05-2029 15:56:50");
    Timestamp result = Dates.toTimestamp(date);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void getDateDiffTest() throws Exception {
    Calendar cal = Calendar.getInstance();
    cal.set(2021, 0, 9);
    Date startDate = cal.getTime();
    cal.set(2021, 0, 30);
    Date endDate = cal.getTime();
    long result = Dates.getDateDiff(startDate, endDate, TimeUnit.MINUTES);
    System.out.println(result);
    Assert.assertNotNull(result);
  }


}
