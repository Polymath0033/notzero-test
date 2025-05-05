package com.polymath.notzerowalletservice.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.polymath.notzerowalletservice.dto.responses.ErrorResponse;
import com.polymath.notzerowalletservice.utils.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomBadRequest.class)
    public ErrorResponse handleBadRequest(CustomBadRequest ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
    }

    @ExceptionHandler(NotFound.class)
    public ErrorResponse handleNotFound(NotFound ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(),System.currentTimeMillis());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = "Invalid input value";
        if(ex.getCause() instanceof InvalidFormatException cause) {
            if(cause.getTargetType()!=null&&cause.getTargetType().isEnum()){
                message = String.format("Invalid value: '%s'. Allowed values for '%s' are '%s",
                        cause.getValue(),cause.getTargetType().getSimpleName(),
                        Arrays.toString(cause.getTargetType().getEnumConstants())
                        );
            }
        }
        return ResponseHandler.handleResponse(null,HttpStatus.BAD_REQUEST,message);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBody.put("message", "An error occurred. Please try again later.");
        responseBody.put("error","Internal Server Error");
        responseBody.put("cause",ex.getCause());
        return new ResponseEntity<>(responseBody,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
