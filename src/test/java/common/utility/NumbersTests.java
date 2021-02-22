package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.Numbers;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class NumbersTests {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testIsNumeric() {
    assertTrue(Numbers.isNumeric("1"));
    assertFalse(Numbers.isNumeric("1,0"));
  }

  @Test
  public void testIsPositiveInt() {
    assertTrue(Numbers.isPositiveInt("1"));
    assertFalse(Numbers.isPositiveInt("1.1"));
    assertFalse(Numbers.isPositiveInt("0"));
    assertFalse(Numbers.isPositiveInt("-1"));
  }

  @Test
  public void testNull2zero() {
    assertEquals(Numbers.null2zero(null), BigDecimal.ZERO);
    assertEquals(Numbers.null2zero(new BigDecimal(1)), new BigDecimal(1));
  }


  @Test
  public void testIsZero() {
    assertFalse(Numbers.isZero(new BigDecimal(1)));
    assertFalse(Numbers.isZero(null));
    assertTrue(Numbers.isZero(new BigDecimal(0)));
    assertFalse(Numbers.isZero(new BigDecimal(-1)));
  }

  @Test
  public void testIsZeroOrNull() {
    assertFalse(Numbers.isZeroOrNull(new BigDecimal(1)));
    assertTrue(Numbers.isZeroOrNull(null));
    assertTrue(Numbers.isZeroOrNull(new BigDecimal(0)));
    assertFalse(Numbers.isZeroOrNull(new BigDecimal(-1)));
  }

  @Test
  public void testIsPositive() {
    assertTrue(Numbers.isPositive(new BigDecimal(1)));
    assertFalse(Numbers.isPositive(null));
    assertFalse(Numbers.isPositive(new BigDecimal(0)));
    assertFalse(Numbers.isPositive(new BigDecimal(-1)));
  }

  @Test
  public void testIsNegative() {
    assertFalse(Numbers.isNegative(new BigDecimal(1)));
    assertFalse(Numbers.isNegative(null));
    assertFalse(Numbers.isNegative(new BigDecimal(0)));
    assertTrue(Numbers.isNegative(new BigDecimal(-1)));
  }

  @Test
  public void testIsZeroOrPositive() {
    assertTrue(Numbers.isZeroOrPositive(new BigDecimal(1)));
    assertFalse(Numbers.isZeroOrPositive(null));
    assertTrue(Numbers.isZeroOrPositive(new BigDecimal(0)));
    assertFalse(Numbers.isZeroOrPositive(new BigDecimal(-1)));
  }

  @Test
  public void testIsZeroOrNegative() {
    assertFalse(Numbers.isZeroOrNegative(new BigDecimal(1)));
    assertFalse(Numbers.isZeroOrNegative(null));
    assertTrue(Numbers.isZeroOrNegative(new BigDecimal(0)));
    assertTrue(Numbers.isZeroOrNegative(new BigDecimal(-1)));
  }

  @Test
  public void testIsNullOrPositive() {
    assertTrue(Numbers.isNullOrPositive(new BigDecimal(1)));
    assertTrue(Numbers.isNullOrPositive(null));
    assertFalse(Numbers.isNullOrPositive(new BigDecimal(0)));
    assertFalse(Numbers.isNullOrPositive(new BigDecimal(-1)));
  }

  @Test
  public void testIsNullOrNegative() {
    assertFalse(Numbers.isNullOrNegative(new BigDecimal(1)));
    assertTrue(Numbers.isNullOrNegative(null));
    assertFalse(Numbers.isNullOrNegative(new BigDecimal(0)));
    assertTrue(Numbers.isNullOrNegative(new BigDecimal(-1)));
  }

  @Test
  public void testEq() {
    assertTrue(Numbers.eq(new BigDecimal(1), new BigDecimal(1)));
    assertFalse(Numbers.eq(new BigDecimal(1), new BigDecimal(-1)));
  }

  @Test
  public void testGt() {
    assertFalse(Numbers.gt(new BigDecimal(1), new BigDecimal(1)));
    assertTrue(Numbers.gt(new BigDecimal(1), new BigDecimal(-1)));
    assertFalse(Numbers.gt(new BigDecimal(-1), new BigDecimal(1)));
  }

  @Test
  public void testGet() {
    assertTrue(Numbers.get(new BigDecimal(1), new BigDecimal(1)));
    assertTrue(Numbers.get(new BigDecimal(1), new BigDecimal(-1)));
    assertFalse(Numbers.get(new BigDecimal(-1), new BigDecimal(1)));
  }

  @Test
  public void testLt() {
    assertFalse(Numbers.lt(new BigDecimal(1), new BigDecimal(1)));
    assertFalse(Numbers.lt(new BigDecimal(1), new BigDecimal(-1)));
    assertTrue(Numbers.lt(new BigDecimal(-1), new BigDecimal(1)));
  }

  @Test
  public void testLet() {
    assertTrue(Numbers.let(new BigDecimal(1), new BigDecimal(1)));
    assertFalse(Numbers.let(new BigDecimal(1), new BigDecimal(-1)));
    assertTrue(Numbers.let(new BigDecimal(-1), new BigDecimal(1)));
  }

  @Test
  public void add2BigDecimalStringTest() throws Exception {
//		BigDecimal result = Numbers.add(BigDecimal.valueOf(1), "1000K");
    BigDecimal result = Numbers.add(BigDecimal.valueOf(1), "1000");
    Assert.assertNotNull(result);
  }

  @Test
  public void scaleTest() throws Exception {
    BigDecimal number = new BigDecimal("123.456000");
    int result = Numbers.scale(number, true);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void add3BigDecimalTest() {
    BigDecimal result = Numbers.add(BigDecimal.valueOf(2.356), BigDecimal.valueOf(1.23), BigDecimal.valueOf(1));
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void roundTest() {
    BigDecimal result = Numbers.round(BigDecimal.valueOf(2.3456789), (byte) 3, RoundingMode.FLOOR);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void divTest() {
    BigDecimal result = Numbers.div(BigDecimal.valueOf(12345.6789), (byte) 5, RoundingMode.CEILING);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void minTest() {
    BigDecimal result = Numbers.min(BigDecimal.valueOf(12345.6789), BigDecimal.valueOf(12345.6799));
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void maxTest() {
    BigDecimal result = Numbers.max(BigDecimal.valueOf(12345.6789), BigDecimal.valueOf(12345.6799));
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void add2BigDecimalTest() {
    BigDecimal result = Numbers.add(BigDecimal.valueOf(12345.6789), BigDecimal.valueOf(12345.6789));
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void sub2BigDecimalTest() {
    BigDecimal result = Numbers.sub(BigDecimal.valueOf(12345.6789), BigDecimal.valueOf(12345.6799));
    System.out.println(result);
    Assert.assertNotNull(result);
  }

  @Test
  public void div2DecimalTest() {
    BigDecimal result = Numbers
        .div(BigDecimal.valueOf(12345.6789), BigDecimal.valueOf(12345.6799), (byte) 50, RoundingMode.DOWN);
    System.out.println(result);
    Assert.assertNotNull(result);
  }

}
