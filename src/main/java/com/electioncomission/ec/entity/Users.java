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
import java.sql.Timestamp;

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

    @Size(min = 10, max = 10, message = "size cannot be greater than ")
    String mobileNumber;

    @Size(min=6, max=6, message = "size cannot be greater than")
    String otp;

    Timestamp otpGenerationTime;

    @Size(max = 30, message = "size cannot be greater than ")
    String userName;

    String password;

    @NotBlank(message = "User Role cannot be null")
    @Size(max = 5, message = "size cannot be greater than ")
    String userRole;

    Integer partId;
    Integer constituencyId;
    Integer districtId;
}
