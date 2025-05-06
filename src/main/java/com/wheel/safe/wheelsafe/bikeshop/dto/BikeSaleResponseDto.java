package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;

import com.wheel.safe.wheelsafe.bicycle.dto.BicycleMapper;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleResponse;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;
import com.wheel.safe.wheelsafe.user.entity.User;

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

    public static BikeSaleResponseDto fromEntity(BikeSale entity) {
        if (entity == null) {
            return null;
        }

        UserProfileDTO userDto = null;
        if (entity.getBuyer() != null) {
            User buyer = entity.getBuyer();
            userDto = UserProfileDTO.builder()
                    .id(buyer.getId())
                    .firstName(buyer.getFirstName())
                    .lastName(buyer.getLastName())
                    .email(buyer.getEmail())
                    .phoneNumber(buyer.getPhoneNumber())
                    .build();
        }

        return BikeSaleResponseDto.builder()
                .id(entity.getId())
                .bicycle(entity.getBicycle() != null ? BicycleMapper.toResponse(entity.getBicycle()) : null)
                .buyer(userDto)
                .bikeShopId(entity.getBikeShop() != null ? entity.getBikeShop().getId() : null)
                .bikeShopName(entity.getBikeShop() != null ? entity.getBikeShop().getName() : null)
                .saleId(entity.getSaleId())
                .salePrice(entity.getSalePrice())
                .saleDate(entity.getSaleDate())
                .saleStatus(entity.getSaleStatus())
                .saleDescription(entity.getSaleDescription())
                .saleImageUrl(entity.getSaleImageUrl())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}