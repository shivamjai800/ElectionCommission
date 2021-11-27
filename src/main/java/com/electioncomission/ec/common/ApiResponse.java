package com.electioncomission.ec.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonIgnoreProperties(value = {"httpStatus"})
public class ApiResponse<T> {
    private ApiError apiError;
    private HttpStatus httpStatus;
    private T data;

    public ApiResponse(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ApiResponse() {}
}
