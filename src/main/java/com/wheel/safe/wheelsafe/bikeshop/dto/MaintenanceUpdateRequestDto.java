package com.wheel.safe.wheelsafe.bikeshop.dto;

import java.time.LocalDateTime;
import java.util.Date;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
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
public class MaintenanceUpdateRequestDto {
    @NotNull(message = "Maintenance ID is required")
    private Long id;

    private Long bikeId;
    private Long customerId;
    private Long bikeShopId;
    private String maintenanceType;
    private MaintenanceStatus maintenanceStatus;
    private LocalDateTime maintenanceDate;
    private String bikeModel;
    private String description;

    @Min(value = 0, message = "Cost must be greater than or equal to 0")
    private Double cost;

    private Date completionDate;
    private String technicianNotes;

    public Maintenance toEntity(Maintenance existingMaintenance) {
        Maintenance maintenance = existingMaintenance != null ? existingMaintenance : new Maintenance();

        if (existingMaintenance != null) {
            maintenance.setId(this.id);
        }
        if (this.bikeId != null)
            maintenance.setBicycle(Bicycle.builder().id(this.bikeId).build());
        if (this.maintenanceType != null)
            maintenance.setMaintenanceType(MaintenanceType.valueOf(this.maintenanceType));
        if (this.maintenanceStatus != null)
            maintenance.setMaintenanceStatus(MaintenanceStatus.valueOf(this.maintenanceStatus.name()));
        if (this.maintenanceDate != null)
            maintenance.setMaintenanceDate(this.maintenanceDate);
        if (this.bikeModel != null)
            maintenance.setMaintenanceDescription(this.description);
        if (this.cost != null)
            maintenance.setMaintenanceCost(this.cost);

        return maintenance;
    }
}