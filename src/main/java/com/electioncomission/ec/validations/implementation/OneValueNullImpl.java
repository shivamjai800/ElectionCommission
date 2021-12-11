package com.electioncomission.ec.validations.implementation;

import com.electioncomission.ec.validations.OneValueNull;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneValueNullImpl implements ConstraintValidator<OneValueNull, Object> {
    String mobileNumber;
    String username;
    @Override
    public void initialize(OneValueNull constraintAnnotation) {
        this.mobileNumber = constraintAnnotation.mobileNumber();
        this.username = constraintAnnotation.username();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object mobileNumberValue = new BeanWrapperImpl(o).getPropertyValue(mobileNumber);
        Object usernameValue = new BeanWrapperImpl(o).getPropertyValue(username);
        return (mobileNumberValue==null && usernameValue!=null) || (mobileNumberValue!=null && usernameValue==null);
    }

}
