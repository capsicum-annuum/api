package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.annotation.DateStartBeforeDateEnd;
import br.com.annuum.capsicum.api.domain.Movement;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateStartBeforeDateEndValidator implements ConstraintValidator<DateStartBeforeDateEnd, Movement> {

    @Override
    public void initialize(DateStartBeforeDateEnd constraintAnnotation) {
    }

    @Override
    public boolean isValid(Movement movement, ConstraintValidatorContext context) {
        return movement.getDateTimeStart().isBefore(movement.getDateTimeEnd());
    }

}
