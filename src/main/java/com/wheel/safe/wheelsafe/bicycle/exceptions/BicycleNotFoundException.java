package com.wheel.safe.wheelsafe.bicycle.exceptions;

public class BicycleNotFoundException extends RuntimeException {
    public BicycleNotFoundException(String message) {
        super(message);
    }

    public BicycleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BicycleNotFoundException withId(Long id) {
        return new BicycleNotFoundException("Bicycle with ID " + id + " not found.");
    }

    public static BicycleNotFoundException withSerialNumber(String serialNumber) {
        return new BicycleNotFoundException("Bicycle with Serial Number " + serialNumber + " not found.");
    }
    public static BicycleNotFoundException withBrand(String brand) {
        return new BicycleNotFoundException("Bicycle with Brand " + brand + " not found.");
    }
    public static BicycleNotFoundException withModel(String model) {
        return new BicycleNotFoundException("Bicycle with Model " + model + " not found.");
    }

}
