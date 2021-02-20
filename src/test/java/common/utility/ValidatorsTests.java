package common.utility;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.yody.common.utility.Validators;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ValidatorsTests {

  @BeforeAll
  public static void setup() {

  }

  @Test
  public void testIsNameValid() {
    assertTrue(Validators.isNameValid("name"));
    assertFalse(Validators.isNameValid("++name"));
  }

  @Test
  public void testIsEmailValid() {
    assertTrue(Validators.isEmailValid("tdhoang96@gmail.com"));
    assertFalse(Validators.isEmailValid("tdhoang96"));
  }

  @Test
  public void testIsUserNameValid() {
    assertTrue(Validators.isUserNameValid("username"));
    assertFalse(Validators.isUserNameValid("user+name"));
  }

  @Test
  public void testIsPasswordValid() {
    assertTrue(Validators.isPasswordValid("password"));
    assertTrue(Validators.isPasswordValid("-password"));
  }

  @Test
  public void testIsPhoneNumberValid() {
    assertTrue(Validators.isPhoneNumberValid("0332934007"));
    assertFalse(Validators.isPhoneNumberValid("0192837465777"));
  }
}
