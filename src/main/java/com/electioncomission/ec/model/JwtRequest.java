package com.electioncomission.ec.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String mobileNumber;
    private String password;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String mobileNumber, String password) {
        this.setMobileNumber(mobileNumber);
        this.setPassword(password);
    }


}
