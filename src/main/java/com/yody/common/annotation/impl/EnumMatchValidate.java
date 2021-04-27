package com.yody.common.annotation.impl;

import com.yody.common.annotation.EnumMatch;
import com.yody.common.enums.BaseEnum;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumMatchValidate implements ConstraintValidator<EnumMatch, String> {

  private Class<? extends BaseEnum<String>> type;

  @Override
  public void initialize(EnumMatch constraintAnnotation) {
    this.type = constraintAnnotation.type();
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
    if (s == null) { return true; }
    try {
      final Method method = type.getDeclaredMethod("parse",String.class);
      Object r = method.invoke(type, s);
      return r != null;
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return true;
  }
}
