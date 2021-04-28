package com.yody.common.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.yody.common.annotation.impl.NotNullWithConditionValidate;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = {NotNullWithConditionValidate.class}
)
public @interface NotNullWithCondition {
  String fieldName();
  String fieldDependName();
  String expectValue();
  String operation();
  String message() default "Required";
  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}



