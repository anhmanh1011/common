package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

public class StringsTests {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testTrim() {
    String s1 = " s";
    String s2 = "s";
    assertEquals(Strings.trim(s1), s2);
  }

  @Test
  public void testTrimToNull() {
    assertEquals(Strings.trimToNull(null), null);
    assertEquals(Strings.trimToNull(""), null);
  }

  @Test
  public void testTrimToEmpty() {
    assertEquals(Strings.trimToEmpty(null), "");
    assertEquals(Strings.trimToEmpty(""), "");
  }

  @Test
  public void testIsEmpty() {
    assertTrue(Strings.isEmpty(null));
    assertTrue(Strings.isEmpty(""));
    assertFalse(Strings.isEmpty("test"));
  }

  @Test
  public void testTrimWithMax() {
    assertEquals(Strings.trim("testTrimWithMax   ", 4), "test");
  }

}
