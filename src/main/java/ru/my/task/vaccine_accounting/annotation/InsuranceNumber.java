package ru.my.task.vaccine_accounting.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = InsuranceNumberConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InsuranceNumber {

    String message() default "{InsuranceNumber}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
