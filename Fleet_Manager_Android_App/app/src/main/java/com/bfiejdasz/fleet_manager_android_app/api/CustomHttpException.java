package com.bfiejdasz.fleet_manager_android_app.api;

public class CustomHttpException extends Exception {
    private final String errorMessage;
    private final int statusCode;

    public CustomHttpException(String errorMessage, int statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

