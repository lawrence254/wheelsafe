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

    public BikeSale toEntity(BikeSale existingBikeSale) {
        BikeSale bikeSale = existingBikeSale != null ? existingBikeSale : new BikeSale();
        
        
        if (existingBikeSale != null) {
            bikeSale.setId(this.id);
        }
        if (this.bicycleId != null) bikeSale.setBicycle(Bicycle.builder().id(this.bicycleId).build());
        if (this.buyerId != null) bikeSale.setBuyer(User.builder().id(this.buyerId).build());
        if (this.bikeShopId != null) bikeSale.setBikeShop(BikeShop.builder().id(this.bikeShopId).build());
        if (this.saleId != null) bikeSale.setSaleId(this.saleId);
        if (this.salePrice != null) bikeSale.setSalePrice(this.salePrice);
        if (this.saleDate != null) bikeSale.setSaleDate(this.saleDate);
        if (this.saleStatus != null) bikeSale.setSaleStatus(this.saleStatus);
        if (this.saleDescription != null) bikeSale.setSaleDescription(this.saleDescription);
        if (this.saleImageUrl != null) bikeSale.setSaleImageUrl(this.saleImageUrl);
        
        return bikeSale;
    }
}
