package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "constituency")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@ToString
public class Constituency {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int constituencyId;

    @NotBlank(message = "constituency name cannot be blank")
    @Size(max = 30, message = "size cannot be greater than ")
    String constituencyName;

    @NotEmpty(message = "district Id cannot be blank")
    @Positive(message = "district Id should be greater than 0")
    int districtId;

    Boolean lock;

    Boolean finalise;
}
