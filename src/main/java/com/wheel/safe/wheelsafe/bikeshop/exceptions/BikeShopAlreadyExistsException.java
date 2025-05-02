package com.wheel.safe.wheelsafe.bikeshop.exceptions;

public class BikeShopAlreadyExistsException extends RuntimeException {
    public BikeShopAlreadyExistsException(String message) {
        super(message);
    }

    public BikeShopAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BikeShopAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    public BikeShopAlreadyExistsException(String message, Object... args) {
        super(String.format(message, args));
    }

}
