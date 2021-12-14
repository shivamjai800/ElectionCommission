package com.electioncomission.ec.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VotersUpdate {
    List<String> eligible;
    List<String> inEligible;
    List<String> avco;
    List<String> nullCategory;
}
