package com.electioncomission.ec.validations;

import com.electioncomission.ec.validations.implementation.OneValueNullImpl;
import com.electioncomission.ec.validations.implementation.RoleWiseRequestImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleWiseRequestImpl.class)
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleWiseRequest {

    String message() default "Invalid Request either send mobileNumber or send the username";
    String role();
    String username();
    String mobileNumber();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
