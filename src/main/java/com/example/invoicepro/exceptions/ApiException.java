package com.example.invoicepro.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
