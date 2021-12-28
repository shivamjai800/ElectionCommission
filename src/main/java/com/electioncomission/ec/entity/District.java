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
import javax.validation.constraints.Size;

@Entity
@Table(name = "district")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
@ToString
public class District {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int districtId;

    @NotBlank(message = " district name cannot be blank")
    @Size(max = 30, message = "size cannot be greater than ")
    String districtName;

    Boolean lock;

}
