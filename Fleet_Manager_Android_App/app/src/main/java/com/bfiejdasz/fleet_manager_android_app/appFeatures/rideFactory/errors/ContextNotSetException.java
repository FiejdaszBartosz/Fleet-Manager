package com.bfiejdasz.fleet_manager_android_app.appFeatures.rideFactory.errors;

public class ContextNotSetException extends Exception {
    private final String errorMessage;

    public ContextNotSetException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
