package com.electioncomission.ec.model;

public class Enums {
    public enum UsersRole{

        BLO("BLO"),
        RO("RO"),
        DEO("DEO"),
        CEO("CEO"),
        SO("SO");
        String value;

        UsersRole(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    };
    public enum VoterCategory{

        AVPD("AVPD"),
        AVCO("AVCO"),
        AVSC("AVSC"),
        AVGE("AVGE"),
        AVEW("AVEW");
        String value;

        VoterCategory(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }


}
