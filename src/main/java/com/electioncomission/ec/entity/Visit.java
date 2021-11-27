package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Table(name = "part")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    String firstVistRemarks;
    Timestamp firstVisitTimestamp;
    String firstVistGpsCoord;
    boolean isVoterExpired;
    boolean secondVisit;
    String secondVisitRemarks;
    Timestamp secondVisitTimestamp;
    String secondVistGpsCoord;
    boolean form12dDelivered;
    String form12dDeliveredRemarks;
    int certificateImageId;
    int eventImageId;
    boolean filledForm12dReceived;
    boolean filledForm12dReceivedRemarks;
    boolean isOptingForPostalBallot;
}
