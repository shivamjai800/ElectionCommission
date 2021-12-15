package com.electioncomission.ec.model;

import com.electioncomission.ec.validations.OneValueNull;
import com.electioncomission.ec.validations.RoleWiseRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@OneValueNull(
        mobileNumber = "mobileNumber",
        username = "username",
        message = "Either send username or mobileNumber!"
)
@RoleWiseRequest(
        mobileNumber = "mobileNumber",
        username = "username",
        role = "userRole",
        message = "According to role the field entered is incorrect"
)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String mobileNumber;
    private String password;
    private String username;
    private String otp;

    @NotBlank
    private String userRole;

    //need default constructor for JSON Parsing

}
