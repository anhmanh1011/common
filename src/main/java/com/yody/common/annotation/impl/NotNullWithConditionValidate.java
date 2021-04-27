package com.yody.common.annotation.impl;

import com.yody.common.annotation.NotNullWithCondition;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class NotNullWithConditionValidate implements ConstraintValidator<NotNullWithCondition, Object> {

  private String fieldName;
  private String fieldDependName;
  private String expectValue;

  @Override
  public void initialize(NotNullWithCondition constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
    this.expectValue = constraintAnnotation.expectValue();
    this.fieldDependName  = constraintAnnotation.fieldDependName();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext context) {
      Object fieldDependValue = new BeanWrapperImpl(o).getPropertyValue(fieldDependName);
      Object fieldValue = new BeanWrapperImpl(o).getPropertyValue(fieldName);
      if(fieldDependValue == null) return true;
      if(fieldDependValue.toString().equals(expectValue) && fieldValue == null){
//        context.disableDefaultConstraintViolation();
//        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
//            .addNode(fieldDependName)
//            .addConstraintViolation();
        return false;
      }
      return true;
  }
}
