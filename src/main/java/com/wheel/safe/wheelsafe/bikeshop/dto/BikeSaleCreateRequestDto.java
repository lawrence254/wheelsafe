package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;

import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeSaleCreateRequestDto {
    @NotNull(message = "Bicycle ID is required")
    private Long bicycleId;

    @NotNull(message = "Buyer ID is required")
    private Long buyerId;

    @NotNull(message = "Bike shop ID is required")
    private Long bikeShopId;

    private Long saleId;

    @NotNull(message = "Sale price is required")
    @Min(value = 0, message = "Sale price must be greater than or equal to 0")
    private Double salePrice;

    @NotNull(message = "Sale date is required")
    private LocalDateTime saleDate;

    @NotNull(message = "Sale status is required")
    private SaleStatus saleStatus;

    private String saleDescription;

    private String saleImageUrl;
}
