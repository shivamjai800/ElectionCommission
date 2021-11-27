package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "district")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class District {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int districtId;

    @NotBlank(message = " district name cannot be blank")
    @Size(max = 30, message = "size cannot be greater than ")
    String districtName;

    @Override
    public String toString() {
        return "District{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                '}';
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
