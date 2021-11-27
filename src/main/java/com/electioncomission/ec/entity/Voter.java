package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "voter")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class Voter {

    @Id
    String epicNo;

    @NotEmpty(message = "list sl number cannot be empty")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int listSlNo;

    @NotEmpty(message = "sl number in part cannot be empty")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int slNoInPart;

    @Size(max = 30, message = "size cannot exceed than")
    String firstName;

    @Size(max = 30, message = "size cannot exceed than")
    String lastName;

    @Size(max = 30, message = "size cannot exceed than")
    String relativeFirstName;

    @Size(max = 30, message = "size cannot exceed than")
    String relativeLastName;

    char rlnType;

    @NotBlank(message = "Gender cannot be empty")
    char gender;

    @NotEmpty(message = "age number cannot be empty")
    @Positive(message = "age number should be greater than 0")
    int age;

    @NotBlank(message = "dob cannot be empty")
    LocalDate dob;

    @NotBlank(message = "c House Number cannot be empty")
    String cHouseNo;

    @Size(min = 10, max = 10, message = "mobile number length should be 10")
    String mobileNo;

    @NotEmpty(message = "constituency id cannot be empty")
    @Positive(message = "constituency id should be greater than 0")
    int constituencyId;

    @NotBlank(message = "constituency name cannot be empty")
    @Size(max = 30, message = "constituency name length should be 10")
    String constituencyName;

    @NotEmpty(message = "partId cannot be empty")
    @Positive(message = "partId should be greater than 0")
    int partId;

    @NotBlank(message = "part name name cannot be empty")
    String partName;

    @NotEmpty(message = "part sl number cannot be empty")
    @Positive(message = "section Number should be greater than 0")
    int sectionNo;

    char pwdYn;

    char ageAbove_80_Yn;

    @NotBlank(message = "category cannot be empty")
    String category;

    String image;


}
