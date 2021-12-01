package com.electioncomission.ec.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TempOtp {

    int mobileNumber;
    @NotBlank
    @Size(min = 6, max = 6, message = "size should be 6")
    String otp;
}


