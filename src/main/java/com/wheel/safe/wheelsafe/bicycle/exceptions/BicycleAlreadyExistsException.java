package com.wheel.safe.wheelsafe.bicycle.exceptions;

public class BicycleAlreadyExistsException extends RuntimeException {
    public BicycleAlreadyExistsException(String message) {
        super(message);
    }

    public BicycleAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BicycleAlreadyExistsException withSerialNumber(String serialNumber) {
        return new BicycleAlreadyExistsException("Bicycle with Serial Number " + serialNumber + " already exists.");
    }

    public static BicycleAlreadyExistsException withBrand(String brand) {
        return new BicycleAlreadyExistsException("Bicycle with Brand " + brand + " already exists.");
    }

    public static BicycleAlreadyExistsException withModel(String model) {
        return new BicycleAlreadyExistsException("Bicycle with Model " + model + " already exists.");
    }
    public static BicycleAlreadyExistsException withId(Long id) {
        return new BicycleAlreadyExistsException("Bicycle with ID " + id + " already exists.");
    }
}
