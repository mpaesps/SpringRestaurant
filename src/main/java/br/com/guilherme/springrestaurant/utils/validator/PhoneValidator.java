package br.com.guilherme.springrestaurant.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<IsValidPhone, String> {
    @Override
    public void initialize(IsValidPhone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        return telefone != null && telefone.matches("\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}");
    }
}
