package com.wheel.safe.wheelsafe.transfer.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDTO {
    @NotNull(message = "Bicycle ID is required")
    private Long bicycleId;
    @NotNull(message = "Previous owner ID is required")
    private Long previousOwnerId;
    @NotNull(message = "New owner ID is required")
    private Long newOwnerId;
    @Size(max = 255, message = "Transfer reason must be less than 255 characters")
    private String transferReason;
    private String verificationCode;
    private String transferStatus;
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be greater than 0")
    private Double cost;
    private String transferDate;
    @Size(max = 500, message = "Transfer description must be less than 500 characters")
    private String transferDescription;
}
