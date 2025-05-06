package com.wheel.safe.wheelsafe.bikeshop.exceptions;

import java.time.LocalDateTime;

import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BikeShopExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(BikeShopNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeShopNotFoundException(BikeShopNotFoundException ex) {
        log.warn("Handling BikeShopNotFoundException: {}", ex.getMessage());
        
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
                
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(BikeSaleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeSaleNotFoundException(BikeSaleNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
                
        return new ResponseEntity<>(error, status);
    }
    
    @ExceptionHandler(MaintenanceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMaintenanceNotFoundException(MaintenanceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
                
        return new ResponseEntity<>(error, status);
    }
    
    @ExceptionHandler(BikeShopAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBikeShopAlreadyExistsException(BikeShopAlreadyExistsException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
                
        return new ResponseEntity<>(error, status);
    }
    
    // Generic exception handler for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse error = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("An unexpected error occurred: " + ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
                
        return new ResponseEntity<>(error, status);
    }
}
