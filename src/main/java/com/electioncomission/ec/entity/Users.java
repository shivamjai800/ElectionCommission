package com.electioncomission.ec.entity;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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

    int partId;
    int constituencyId;
    int districtId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
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
