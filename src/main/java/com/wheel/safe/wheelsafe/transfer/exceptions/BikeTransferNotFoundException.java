package com.wheel.safe.wheelsafe.transfer.exceptions;

public class BikeTransferNotFoundException extends RuntimeException {
    public BikeTransferNotFoundException(Long transferId) {
        super("Bike transfer with ID " + transferId + " not found.");
    }

    public BikeTransferNotFoundException(String message) {
        super(message);
    }
    public BikeTransferNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
