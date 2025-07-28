package com.wheel.safe.wheelsafe.bikeshop.dto;

import org.springframework.stereotype.Component;

import com.wheel.safe.wheelsafe.bicycle.dto.BicycleMapper;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeSale;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;
import com.wheel.safe.wheelsafe.user.entity.User;

@Component
public class BikeSaleMapper {

    public BikeSaleResponseDto fromEntity(BikeSale entity) {
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