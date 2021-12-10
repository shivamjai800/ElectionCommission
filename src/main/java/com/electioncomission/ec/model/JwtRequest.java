package com.electioncomission.ec.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String mobileNumber;
    private String password;
    private String username;
    private String otp;
    private String role;

    //need default constructor for JSON Parsing

}
