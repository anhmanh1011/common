package com.yody.common.utility;

public class Validators {

  public static final String PASSWORD_REGEX = "\\S+";
  public static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";
  public static final String PHONENUMBER_REGEX = "(84|0[3|5|7|8|9])+([0-9]{8})\\b"; // only for VN
  public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
  public static final String NAME_REGEX = "^[a-zA-Z0-9_ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ ]+$";

  public static boolean isNameValid(String name) {
    if (Strings.isEmpty(name)) {
      return false;
    }
    return name.matches(NAME_REGEX);
  }

  public static boolean isEmailValid(String email) {
    if (Strings.isEmpty(email)) {
      return false;
    }
    return email.matches(EMAIL_REGEX);
  }

  public static boolean isUserNameValid(String userName) {
    if (Strings.isEmpty(userName)) {
      return false;
    }
    return userName.matches(USERNAME_REGEX);
  }

  public static boolean isPasswordValid(String password) {
    if (Strings.isEmpty(password)) {
      return false;
    }
    return password.matches(PASSWORD_REGEX);
  }

  public static boolean isPhoneNumberValid(String phoneNumber) {
    if (Strings.isEmpty(phoneNumber)) {
      return false;
    }
    return phoneNumber.matches(PHONENUMBER_REGEX);
  }

}
