package com.electioncomission.ec.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportFilter {

    String voterType;
    String voterEligiblity;
    String physicallyMet;
    String voterNotFound;
    String form12dDelivered;
    String form12dNotDelivered;
    String form12dCollected;
    String form12NotCollected;
    String voteCastId;
    String locked;
}
