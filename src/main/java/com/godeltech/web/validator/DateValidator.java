package com.godeltech.web.validator;

import com.godeltech.exception.DateTimeMismatchException;
import com.godeltech.web.validator.annotation.DateValidation;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RequiredArgsConstructor
public class DateValidator implements ConstraintValidator<DateValidation, String> {
    @Override
    public boolean isValid(String customDateField, ConstraintValidatorContext cxt) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try
        {
            LocalDateTime.parse(customDateField,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        }
        catch (Exception e)
        {
            throw new DateTimeMismatchException(cxt.getDefaultConstraintMessageTemplate());
        }
    }
}
