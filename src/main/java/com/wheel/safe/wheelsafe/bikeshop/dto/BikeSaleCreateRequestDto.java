package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.user.entity.User;

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

    public BikeSale toEntity() {
        return BikeSale.builder()
                .bicycle(Bicycle.builder().id(bicycleId).build())
                .buyer(User.builder().id(buyerId).build())
                .bikeShop(BikeShop.builder().id(bikeShopId).build())
                .saleId(saleId)
                .salePrice(salePrice)
                .saleDate(saleDate)
                .saleStatus(saleStatus)
                .saleDescription(saleDescription)
                .saleImageUrl(saleImageUrl)
                .build();
    }
}
