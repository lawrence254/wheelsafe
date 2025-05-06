package com.wheel.safe.wheelsafe.bikeshop.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

public class BikeShopNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public BikeShopNotFoundException(String message) {
        super(message);
    }
    public BikeShopNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    

}
