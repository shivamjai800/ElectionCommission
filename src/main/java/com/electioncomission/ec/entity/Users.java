package com.electioncomission.ec.entity;


import com.sun.istack.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;

    @Size(max = 30, message = "size cannot be greater than ")
    String firstName;

    @Size(max = 30, message = "size cannot be greater than ")
    String lastName;

    @NotBlank(message = "Mobile Number cannot be blank")
    @Size(min = 10, max = 10, message = "size cannot be greater than ")
    String mobileNumber;

    @NotBlank(message = "User Role cannot be null")
    @Size(max = 5, message = "size cannot be greater than ")
    String userRole;

    int partId;
    int constituencyId;
    int districtId;
}
