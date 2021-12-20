package com.electioncomission.ec.customExceptionHandler;

import com.electioncomission.ec.common.ApiError;
import com.electioncomission.ec.common.ApiErrorCode;
import com.electioncomission.ec.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiResponse apiResponse = new ApiResponse(new ApiError(ApiErrorCode.UNKNOWN_ERROR));
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.getApiError().setSubMessage(String.join(" ",errors));

        return handleExceptionInternal(
                ex, apiResponse, headers, apiResponse.getHttpStatus(), request);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {


        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        ApiResponse apiResponse =
                new ApiResponse(new ApiError(ApiErrorCode.UNKNOWN_ERROR));
        apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        apiResponse.getApiError().setSubMessage(String.join(" ",errors));
        log.error(apiResponse.getApiError().getSubMessage());
        ex.printStackTrace();
        return new ResponseEntity<Object>(
                apiResponse, new HttpHeaders(), apiResponse.getHttpStatus());
    }
}
