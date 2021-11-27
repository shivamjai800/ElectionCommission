package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Table(name = "constituency")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Constituency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int constituencyId;

    @NotBlank(message = "constituency name cannot be blank")
    @Size(max = 30, message = "size cannot be greater than ")
    String constituencyName;

    @NotEmpty(message = "district Id cannot be blank")
    @Positive(message = "district Id should be greater than 0")
    int districtId;
}
