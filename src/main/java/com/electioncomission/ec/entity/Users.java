package com.electioncomission.ec.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString
public class Users {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    String password;

    Integer partId;
    Integer constituencyId;
    Integer districtId;


}
