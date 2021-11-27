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
@Table(name = "part")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Part {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int partId;

    @NotEmpty(message = "constituency part number cannot be empty")
    @Positive(message = "constituency part number  should be greater than 0")
    int constituencyPartNo;

    @NotBlank(message = "part name cannot be blank")
    @Size(max = 250, message = "size cannot be greater than ")
    String partName;

    @NotEmpty(message = "constituency Id cannot be empty")
    @Positive(message = "constituency id should be greater than 0")
    int constituencyId;

    @NotEmpty(message = "district id cannot be empty")
    @Positive(message = "constituency id should be greater than 0")
    int districtId;

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getConstituencyPartNo() {
        return constituencyPartNo;
    }

    public void setConstituencyPartNo(int constituencyPartNo) {
        this.constituencyPartNo = constituencyPartNo;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getConstituencyId() {
        return constituencyId;
    }

    public void setConstituencyId(int constituencyId) {
        this.constituencyId = constituencyId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }
}
