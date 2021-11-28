package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "part")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", constituencyPartNo=" + constituencyPartNo +
                ", partName='" + partName + '\'' +
                ", constituencyId=" + constituencyId +
                ", districtId=" + districtId +
                '}';
    }
}
