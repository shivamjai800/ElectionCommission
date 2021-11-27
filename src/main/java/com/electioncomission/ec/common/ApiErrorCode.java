package com.electioncomission.ec.common;

public enum ApiErrorCode {
    // Voter  Api Error Code

    VOTER_NOT_FOUND(100, "Voter Not Found");

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

