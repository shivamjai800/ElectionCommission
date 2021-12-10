package com.electioncomission.ec.validations.implementation;

import com.electioncomission.ec.validations.RoleWiseRequest;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RoleWiseRequestImpl implements ConstraintValidator<RoleWiseRequest, Object> {
    String role;
    String mobileNumber;
    String username;

    @Override
    public void initialize(RoleWiseRequest constraintAnnotation) {
        this.mobileNumber = constraintAnnotation.mobileNumber();
        this.username = constraintAnnotation.username();
        this.role = constraintAnnotation.role();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object mobileNumberValue = new BeanWrapperImpl(o).getPropertyValue(mobileNumber);
        Object usernameValue = new BeanWrapperImpl(o).getPropertyValue(username);
        Object roleValue = new BeanWrapperImpl(o).getPropertyValue(role);

        return ((roleValue.equals("BLO") && mobileNumberValue!=null) || ((roleValue.equals("RO") || roleValue.equals("DEO") || roleValue.equals("CEO")) && usernameValue!=null));
    }

}
