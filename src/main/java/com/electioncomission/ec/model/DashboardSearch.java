package com.electioncomission.ec.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DashboardSearch {
    Integer districtId;
    Integer constituencyId;
    Integer partId;
}
