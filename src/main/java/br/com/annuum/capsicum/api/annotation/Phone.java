package br.com.annuum.capsicum.api.annotation;
import br.com.annuum.capsicum.api.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {

    String message() default "O telefone informado não é válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
