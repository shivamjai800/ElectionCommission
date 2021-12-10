package com.electioncomission.ec.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
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
