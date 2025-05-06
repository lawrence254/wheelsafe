package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;
import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceType;

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
public class MaintenanceCreateRequestDto {
    @NotNull(message = "Bike ID is required")
    private Long bikeId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Bike shop ID is required")
    private Long bikeShopId;

    @NotNull(message = "Service type is required")
    private String serviceType;

    @NotNull(message = "Status is required")
    private MaintenanceStatus status;

    @NotNull(message = "Maintenance date is required")
    private LocalDateTime maintenanceDate;

    private String maintenanceDescription;
    private MaintenanceType maintenanceType;

    @Min(value = 0, message = "Cost must be greater than or equal to 0")
    private Double cost;

    private Date completionDate;
    private String technicianNotes;
    private String maintenanceImageUrl;

    public Maintenance toEntity() {
        return Maintenance.builder()
                .bicycle(Bicycle.builder().id(this.bikeId).build())
                .bikeShop(BikeShop.builder().id(this.bikeShopId).build())
                .maintenanceStatus(this.status)
                .maintenanceType(this.maintenanceType)
                .maintenanceDate(this.maintenanceDate)
                .maintenanceDescription(this.maintenanceDescription)
                .maintenanceImageUrl(this.maintenanceImageUrl)
                .maintenanceCost(this.cost)
                .build();
    }
}
