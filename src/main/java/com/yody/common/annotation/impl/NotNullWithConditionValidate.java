package com.yody.common.annotation.impl;

import com.yody.common.annotation.NotNullWithCondition;
import com.yody.common.enums.SearchOperation;
import com.yody.common.utility.Numbers;
import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class NotNullWithConditionValidate implements ConstraintValidator<NotNullWithCondition, Object> {

  private String fieldName;
  private String fieldDependName;
  private String expectValue;
  private String operation;

  @Override
  public void initialize(NotNullWithCondition constraintAnnotation) {
    this.fieldName = constraintAnnotation.fieldName();
    this.expectValue = constraintAnnotation.expectValue();
    this.fieldDependName = constraintAnnotation.fieldDependName();
    this.operation = constraintAnnotation.operation();
  }

  @Override
  public boolean isValid(Object o, ConstraintValidatorContext context) {
    Object fieldDependValue = new BeanWrapperImpl(o).getPropertyValue(fieldDependName);
    Object fieldValue = new BeanWrapperImpl(o).getPropertyValue(fieldName);
    SearchOperation op = SearchOperation.parse(this.operation);
    if (op != null) {
      if (fieldDependValue == null) { return true; }
      boolean flag = false;
      switch (op) {
        case EQUAL:
          flag = fieldDependValue.toString().equals(expectValue);
          break;
        case NOT_EQUAL:
          flag = !fieldDependValue.toString().equals(expectValue);
          break;
        case GREATER_THAN:
        case LESS_THAN:
          if (!Numbers.isNumeric(fieldDependValue.toString()) || !Numbers.isNumeric(expectValue.toString())) {
            return true;
          }
          BigDecimal v1 = new BigDecimal(fieldDependValue.toString());
          BigDecimal v2 = new BigDecimal(expectValue.toString());

          flag = op == SearchOperation.GREATER_THAN ? v1.compareTo(v2) > 0 : v1.compareTo(v2) < 0;
          break;
      }
      if (flag && fieldValue == null) { return false; }
    }
    return true;
  }
}
