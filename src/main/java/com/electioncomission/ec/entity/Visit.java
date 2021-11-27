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
import java.sql.Timestamp;

@Entity
@Table(name = "visit")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Getter
@Setter
public class Visit {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int visitId;

    @NotBlank(message = "voter Epic Number cannot be empty ")
    @Size(min=10,max = 10, message = "the size will be 10 only ")
    String voterEpicNo;

    int voterSlNo;

    @Size(max = 4, message = "size cannot be greater than ")
    String voterCategory;

    @NotEmpty(message = "blo id cannot be empty")
    @Positive(message = "bloId should be greater than 0")
    int bloId;

    @Size(min = 10, max = 10, message = "size cannot be greater than ")
    String voterMobileNo;
    boolean firstVisit;
    String firstVisitRemarks;
    Timestamp firstVisitTimestamp;
    float firstVisitGpsCoordLat;
    float firstVisitGpsCoordLon;
    boolean isVoterExpired;
    boolean secondVisit;
    String secondVisitRemarks;
    Timestamp secondVisitTimestamp;
    float secondVisitGpsCoordLat;
    float secondVisitGpsCoordLon;
    boolean form_12dDelivered;
    String form_12dDeliveredRemarks;
    int certificateImageId;
    int eventImageId;
    boolean filledForm_12dReceived;
    boolean filledForm_12dReceivedRemarks;
    boolean isOptingForPostalBallot;
}
