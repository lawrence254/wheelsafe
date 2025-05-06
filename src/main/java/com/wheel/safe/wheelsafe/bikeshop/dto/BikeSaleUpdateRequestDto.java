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
public class BikeSaleUpdateRequestDto {
    @NotNull(message = "BikeSale ID is required")
    private Long id;

    private Long bicycleId;

    private Long buyerId;

    private Long bikeShopId;

    private Long saleId;

    @Min(value = 0, message = "Sale price must be greater than or equal to 0")
    private Double salePrice;

    private LocalDateTime saleDate;

    private SaleStatus saleStatus;

    private String saleDescription;

    private String saleImageUrl;
}
