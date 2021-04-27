package com.yody.common.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.yody.common.annotation.impl.NotNullWithConditionValidate;
import com.yody.common.enums.BaseEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
    validatedBy = {NotNullWithConditionValidate.class}
)
public @interface EnumMatch {
  Class<? extends BaseEnum<String>> type();
  String message() default "Enum không tồn tại";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
