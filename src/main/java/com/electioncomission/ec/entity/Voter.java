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

    public String getEpicNo() {
        return epicNo;
    }

    public void setEpicNo(String epicNo) {
        this.epicNo = epicNo;
    }

    public int getListSlNo() {
        return listSlNo;
    }

    public void setListSlNo(int listSlNo) {
        this.listSlNo = listSlNo;
    }

    public int getSlNoInPart() {
        return slNoInPart;
    }

    public void setSlNoInPart(int slNoInPart) {
        this.slNoInPart = slNoInPart;
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

    public String getRelativeFirstName() {
        return relativeFirstName;
    }

    public void setRelativeFirstName(String relativeFirstName) {
        this.relativeFirstName = relativeFirstName;
    }

    public String getRelativeLastName() {
        return relativeLastName;
    }

    public void setRelativeLastName(String relativeLastName) {
        this.relativeLastName = relativeLastName;
    }

    public char getRlnType() {
        return rlnType;
    }

    public void setRlnType(char rlnType) {
        this.rlnType = rlnType;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getcHouseNo() {
        return cHouseNo;
    }

    public void setcHouseNo(String cHouseNo) {
        this.cHouseNo = cHouseNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

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

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(int sectionNo) {
        this.sectionNo = sectionNo;
    }

    public char getPwdYn() {
        return pwdYn;
    }

    public void setPwdYn(char pwdYn) {
        this.pwdYn = pwdYn;
    }

    public char getAgeAbove_80_Yn() {
        return ageAbove_80_Yn;
    }

    public void setAgeAbove_80_Yn(char ageAbove_80_Yn) {
        this.ageAbove_80_Yn = ageAbove_80_Yn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
