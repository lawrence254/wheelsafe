package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;

import com.wheel.safe.wheelsafe.bicycle.dto.BicycleResponse;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeSaleResponseDto {
    private Long id;
    private BicycleResponse bicycle;
    private UserProfileDTO buyer;
    private Long bikeShopId;
    private String bikeShopName;
    private Long saleId;
    private Double salePrice;
    private LocalDateTime saleDate;
    private SaleStatus saleStatus;
    private String saleDescription;
    private String saleImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}