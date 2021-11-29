package com.example.test.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class BirthDateValidator implements ConstraintValidator<BirthDate, LocalDate> {
    @Override
    public boolean isValid(final LocalDate valueToValidate, final ConstraintValidatorContext context) {
        LocalDate today = LocalDate.now();
        Period age = Period.between(valueToValidate, today);

        return age.getYears() >= 18;
    }
}
