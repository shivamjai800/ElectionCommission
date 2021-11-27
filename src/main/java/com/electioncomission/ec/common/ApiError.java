package com.electioncomission.ec.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApiError {
    private String message;
    private String subMessage;
    private int errorCodeValue;

    public ApiError(String subMessage, ApiErrorCode code) {
        this.subMessage = subMessage;
        this.message = code.getText();
        this.errorCodeValue = code.getErrorCodeValue();
    }

    public ApiError() {}
}
