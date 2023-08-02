package br.com.guilherme.springrestaurant.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Constraint(validatedBy = PhoneValidator.class)
public @interface IsValidPhone {

    String message() default "{IsValidPhone.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
