package com.electioncomission.ec.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

public enum SuccessMessage {

    //visit related
    FIRST_VISIT_CREATED_SUCCESSFULLY("first visit created successfully for the voter"),
    SECOND_VISIT_CREATED_SUCCESSFULLY("second visit created successfully for the voter"),;


    private String message;

    SuccessMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
