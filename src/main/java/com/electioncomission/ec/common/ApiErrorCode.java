package com.electioncomission.ec.common;

public enum ApiErrorCode {
    // Voter  Api Error Code

    VOTER_NOT_FOUND(100, "Voter not found"),
    VOTER_EXPIRED(101, "voter is expired"),
    SECOND_VISIT_COMPLETED_EARLIER(102,"all visit completed earlier"),
    INVALID_USER_CREDENTIALS(103, "Username password are incorrect"),
    OTP_IS_INVALID(104,"otp is invalid"),
    OTP_EXPIRED(105,"otp expired earlier"),
    USER_NOT_PERMITTED(106,"user not permitted to do following thing please check"),
    USER_NOT_LOGGED_IN(107,"user not logged in"),
    MOBILE_NUMBER_ALREADY_EXISTS(108,"mobile number already exists please change the number"),
    EPICNO_IS_INVALID(109, "epic Number is invalid"),
    SPECIFICATION_IS_NULL(110,"Specification is null"),
    VOTE_ALREADY_CASTED(111, "Vote already casted by the voter"),
    REQUEST_BODY_SENT_HAS_ERRORS(112," Request Body sent has errors"),
    JSON_STRING_PARSE_FAILED(113, "json string to parse failed "),
    VOTER_OUT_OF_BLO_PART(114, "voter(s) is out of blo part please check the epic number or partId again"),
    VISIT_NOT_FOUND(115,"visit not found"),
    VISIT_OUT_OF_LOGGED_USER_AREA(114, "voter(s) is out of user area please check the epic number or partId again"),
    USER_DOES_NOT_EXISTS(115,"user with given mobile number does not exits"),
    OTP_CREATED_EARLIER(116,"otp has been sent already please wait for 5 minutes after the request has been sent"),
    CANNOT_GENERATE_OTP(117,"cannot generate otp"),
    BINDING_ERROR(118, "binding error");
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

