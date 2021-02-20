package common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.yody.common.utility.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StringsTest {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testTrim() {
    String s1 = " s";
    String s2 = "s";
    assertEquals(Strings.trim(s1), s2);
  }

}
