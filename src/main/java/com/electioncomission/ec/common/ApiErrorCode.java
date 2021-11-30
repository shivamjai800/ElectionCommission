package com.electioncomission.ec.common;

public enum ApiErrorCode {
    // Voter  Api Error Code

    VOTER_NOT_FOUND(100, "Voter not found"),
    VOTER_EXPIRED(101, "voter is expired"),
    SECOND_VISIT_COMPLETED_EARLIER(102,"all visit completed earlier");

    private int value;
    private String text;

    ApiErrorCode(int value, String text) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return this.text;
    }

    public int getErrorCodeValue() {
        return this.value;
    }
}

