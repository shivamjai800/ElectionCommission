package com.electioncomission.ec.model;

public enum OfficerRoles {
    BLO("booth_level_officer"),RO("regional_officer"),DEO("district_level_officer"),SEO("state_officer");

    String value;
    OfficerRoles(String value)
    {
        this.value =value;
    }

}
