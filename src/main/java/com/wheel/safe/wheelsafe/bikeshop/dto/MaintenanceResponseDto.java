package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleMapper;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleResponse;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;
import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceResponseDto {
    private Long id;
    private BicycleResponse bike;
    private BikeShopResponse bikeShop;
    private MaintenanceType maintenanceType;
    private MaintenanceStatus status;
    private LocalDateTime maintenanceDate;
    private String description;
    private Double cost;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static MaintenanceResponseDto fromEntity(Maintenance entity) {
        if (entity == null) {
            return null;
        }

        BikeShop shop = entity.getBikeShop();
        return MaintenanceResponseDto.builder()
                .id(entity.getId())
                .bike(entity.getBicycle() != null ? BicycleMapper.toResponse(entity.getBicycle()) : null)
                .bikeShop(BikeShopResponse.fromEntity(entity.getBikeShop()))
                .maintenanceType(entity.getMaintenanceType())
                .status(entity.getMaintenanceStatus())
                .maintenanceDate(entity.getMaintenanceDate())
                .description(entity.getMaintenanceDescription())
                .cost(entity.getMaintenanceCost())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}