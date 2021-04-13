package com.yody.common.utility;

import com.yody.common.enums.SearchOperation;
import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

public final class Strings extends StringUtils {

  public static String trim(final String s) {
    return ((s == null) ? null : s.trim());
  }

  public static String trimToNull(String s) {
    s = trim(s);
    return isEmpty(s) ? null : s;
  }
  public static String trimToEmpty(String s) {
    if (s == null) {
      return "";
    }
    return s.trim();
  }

  public static String trim(String s, int max) {
    if (s == null) {
      return null;
    } else {
      s = s.trim();
    }
    if (s.length() > max) {
      s = s.substring(0, max);
    }
    return s;
  }

  public static boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }

  public static String snakeToCamel(String str) {
    if(isEmpty(str)) return  null;
    StringBuilder builder = new StringBuilder(str);
    for (int i = 0; i < builder.length(); i++) {

      if (builder.charAt(i) == '_') {
        builder.deleteCharAt(i);
        builder.replace(i, i + 1, String.valueOf(Character.toUpperCase(builder.charAt(i))));
      }
    }
    return builder.toString();
  }

  public static boolean compare(String s1 , String s2){
      if(isEmpty(s1) && isEmpty(s2)) return true;
      if(isEmpty(s1) || isEmpty(s2)) return false;
      return s1.equals(s2);
  }
}
