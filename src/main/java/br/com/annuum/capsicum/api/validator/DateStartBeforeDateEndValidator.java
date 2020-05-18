package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.annotation.DateStartBeforeDateEnd;
import br.com.annuum.capsicum.api.domain.EventPeriod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateStartBeforeDateEndValidator implements ConstraintValidator<DateStartBeforeDateEnd, EventPeriod> {

    @Override
    public void initialize(DateStartBeforeDateEnd constraintAnnotation) {
    }

    @Override
    public boolean isValid(EventPeriod eventPeriod, ConstraintValidatorContext context) {
        return eventPeriod.getDateTimeStart().isBefore(eventPeriod.getDateTimeEnd());
    }

}
