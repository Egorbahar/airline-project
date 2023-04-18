package com.godeltech.web.validator;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.DateTimeMismatchException;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RequiredArgsConstructor

public class DateValidator implements ConstraintValidator<DateValidation, String> {
    private final LocalMessageSource localMessageSource;
    @Override
    public boolean isValid(String customDateField,
                           ConstraintValidatorContext cxt) {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try
        {
            LocalDateTime localDateTime = LocalDateTime.parse(customDateField,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return true;
        }
        catch (Exception e)
        {
            throw new DateTimeMismatchException(localMessageSource.getMessage("flight.format", new Object[]{}));
        }
    }
}
