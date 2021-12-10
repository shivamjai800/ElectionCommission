package com.electioncomission.ec.validations;

import com.electioncomission.ec.validations.implementation.OneValueNullImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = OneValueNullImpl.class)
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneValueNull {

    String message() default "Invalid Request either send mobileNumber or send the username";
    String mobileNumber();
    String username();

    Class<?>[] groups() default {};
//
    Class<? extends Payload>[] payload() default {};

}
