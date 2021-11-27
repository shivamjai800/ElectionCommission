package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "constituency")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public String getConstituencyName() {
        return constituencyName;
    }

    public void setConstituencyName(String constituencyName) {
        this.constituencyName = constituencyName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
}
