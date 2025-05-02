package com.wheel.safe.wheelsafe.bikeshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BikeShopExceptionHandler {

    @ExceptionHandler(BikeShopNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeShopNotFoundException(BikeShopNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BikeSaleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeSaleNotFoundException(BikeSaleNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MaintenanceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMaintenanceNotFoundException(MaintenanceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BikeShopAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBikeShopAlreadyExistsException(BikeShopAlreadyExistsException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
