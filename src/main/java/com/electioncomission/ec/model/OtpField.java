package com.electioncomission.ec.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class OtpField {

    @NotBlank
    @Size(min = 10, max = 10, message = "size should be 6")
    String mobileNumber;
}


