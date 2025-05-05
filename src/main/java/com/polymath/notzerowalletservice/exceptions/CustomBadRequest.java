package com.polymath.notzerowalletservice.exceptions;

public class CustomBadRequest extends RuntimeException {
    public CustomBadRequest(String message) {
        super(message);
    }
}
