package com.wheel.safe.wheelsafe.bikeshop.exceptions;

public class BikeSaleNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BikeSaleNotFoundException(String message) {
        super(message);
    }

    public BikeSaleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public static BikeSaleNotFoundException withId(Long id, Throwable cause) {
        return new BikeSaleNotFoundException("BikeSale with ID " + id + " not found.", cause);
    }

    public static BikeSaleNotFoundException withId(Long id) {
        return new BikeSaleNotFoundException("BikeSale with ID " + id + " not found.");
    }
    public static BikeSaleNotFoundException withCustomerId(Long customerId) {
        return new BikeSaleNotFoundException("BikeSale with Customer ID " + customerId + " not found.");
    }
    public static BikeSaleNotFoundException withBikeShopId(Long bikeShopId) {
        return new BikeSaleNotFoundException("BikeSale with BikeShop ID " + bikeShopId + " not found.");
    }
    public static BikeSaleNotFoundException withBikeModel(String bikeModel) {
        return new BikeSaleNotFoundException("BikeSale with Bike Model " + bikeModel + " not found.");
    }

}
