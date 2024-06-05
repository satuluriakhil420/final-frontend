package com.ngo.skyfoundation.exception;

public class ErrorMessage extends Exception {
    private final String message;


    public ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
