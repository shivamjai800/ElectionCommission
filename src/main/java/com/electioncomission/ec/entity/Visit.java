package com.electioncomission.ec.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int visitId;

    @NotBlank(message = "voter Epic Number cannot be empty ")
    @Size(min=10,max = 10, message = "the size will be 10 only ")
    String voterEpicNo;

    int voterSlNo;

    @Size(max = 4, message = "size cannot be greater than ")
    String voterCategory;

    @NotNull(message = "blo id cannot be empty")
    @Positive(message = "bloId should be greater than 0")
    int bloId;

    @Size(min = 10, max = 10, message = "Minimum size for mobile number should be 10 ")
    String voterMobileNo;
    boolean isPhysicallyMet;
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

    @Override
    public String toString() {
        return "Visit{" +
                "visitId=" + visitId +
                ", voterEpicNo='" + voterEpicNo + '\'' +
                ", voterSlNo=" + voterSlNo +
                ", voterCategory='" + voterCategory + '\'' +
                ", bloId=" + bloId +
                ", voterMobileNo='" + voterMobileNo + '\'' +
                ", isPhysicallyMet=" + isPhysicallyMet +
                ", firstVisit=" + firstVisit +
                ", firstVisitRemarks='" + firstVisitRemarks + '\'' +
                ", firstVisitTimestamp=" + firstVisitTimestamp +
                ", firstVisitGpsCoordLat=" + firstVisitGpsCoordLat +
                ", firstVisitGpsCoordLon=" + firstVisitGpsCoordLon +
                ", isVoterExpired=" + isVoterExpired +
                ", secondVisit=" + secondVisit +
                ", secondVisitRemarks='" + secondVisitRemarks + '\'' +
                ", secondVisitTimestamp=" + secondVisitTimestamp +
                ", secondVisitGpsCoordLat=" + secondVisitGpsCoordLat +
                ", secondVisitGpsCoordLon=" + secondVisitGpsCoordLon +
                ", form_12dDelivered=" + form_12dDelivered +
                ", form_12dDeliveredRemarks='" + form_12dDeliveredRemarks + '\'' +
                ", certificateImageId=" + certificateImageId +
                ", eventImageId=" + eventImageId +
                ", filledForm_12dReceived=" + filledForm_12dReceived +
                ", filledForm_12dReceivedRemarks=" + filledForm_12dReceivedRemarks +
                ", isOptingForPostalBallot=" + isOptingForPostalBallot +
                '}';
    }
}
