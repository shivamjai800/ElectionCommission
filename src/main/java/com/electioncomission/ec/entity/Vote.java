package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "vote")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class Vote {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int voteId;

    @NotBlank(message = "voter epic Number cannot be blank")
    @Size(min = 10, max = 10, message = "the size can be 10")
    String voterEpicNo;

    @NotBlank(message = "voterCategory cannot be blank")
    @Size(max = 4, message = "voter category cannot be more than 4")
    String voterCategory;

    @NotBlank(message = "voter first name cannot be blank")
    @Size(max = 30, message = "voter gfirst name cannot be more than 30")
    String voterFirstName;

    @NotBlank(message = "voter last name cannot be blank")
    @Size(max = 30, message = "voter category cannot be more than 30")
    String voterLastName;

    @NotEmpty(message = "voterCategory cannot be empty")
    int bloId;

    boolean isVoteCasted;
    Timestamp voteCastTimestamp;
    String gpsCoordLat;
    String gpsCoordLon;
    String documentProducedForIdentification;

}
