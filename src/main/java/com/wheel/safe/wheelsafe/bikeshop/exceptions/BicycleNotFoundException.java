package com.wheel.safe.wheelsafe.bikeshop.exceptions;


public class BicycleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BicycleNotFoundException(String message) {
        super(message);
    }

    public BicycleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BicycleNotFoundException withId(Long id, Throwable cause) {
        return new BicycleNotFoundException("Bicycle with ID " + id + " not found.", cause);
    }

    public static BicycleNotFoundException withId(Long id) {
        return new BicycleNotFoundException("Bicycle with ID " + id + " not found.");
    }

    public static BicycleNotFoundException withSerialNumber(String serialNumber) {
        return new BicycleNotFoundException("Bicycle with Serial Number " + serialNumber + " not found.");
    }

}
