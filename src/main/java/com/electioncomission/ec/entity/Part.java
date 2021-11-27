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

@Table(name = "part")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int partId;

    @NotEmpty(message = "constituency part number cannot be empty")
    @Positive(message = "constituency part number  should be greater than 0")
    int constituencyPartNumber;

    @NotBlank(message = "part name cannot be blank")
    @Size(max = 250, message = "size cannot be greater than ")
    String partName;

    @NotEmpty(message = "constituency Id cannot be empty")
    @Positive(message = "constituency id should be greater than 0")
    int constituencyId;

    @NotEmpty(message = "district id cannot be empty")
    @Positive(message = "constituency id should be greater than 0")
    int districtId;


}
