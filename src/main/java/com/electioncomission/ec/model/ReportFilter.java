package com.electioncomission.ec.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReportFilter {
    Integer districtId;
    Integer constituencyId;
    Integer partId;
    String voterCategory;
    String voterEligiblity;
    String physicallyMet;
    String voterNotFound;
    String form_12dDelivered;
    String form_12dNotDelivered;
    String filledForm_12dReceived;
    String filledForm_12dNotReceived;
    String voteCasted;
    String locked;
}
