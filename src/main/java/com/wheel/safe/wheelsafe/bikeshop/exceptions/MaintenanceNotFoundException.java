package com.wheel.safe.wheelsafe.bikeshop.exceptions;

public class MaintenanceNotFoundException extends RuntimeException {
    public MaintenanceNotFoundException(String message) {
        super(message);
    }

    public MaintenanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaintenanceNotFoundException(Throwable cause) {
        super(cause);
    }

}
