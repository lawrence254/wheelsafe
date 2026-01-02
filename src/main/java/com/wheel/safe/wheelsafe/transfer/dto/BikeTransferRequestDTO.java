package com.wheel.safe.wheelsafe.transfer.dto;

import java.time.LocalDate;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.transfer.entity.BikeTransfer;
import com.wheel.safe.wheelsafe.transfer.entity.TransferStatus;
import com.wheel.safe.wheelsafe.user.entity.User;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class BikeTransferRequestDTO {

    @NotNull(message = "Bicycle ID is required")
    private Long bicycleId;
    @NotNull(message = "Previous owner ID is required")
    private Long previousOwnerId;
    @NotNull(message = "New owner ID is required")
    private Long newOwnerId;
    @Size(max = 255, message = "Transfer reason must be less than 255 characters")
    private String transferReason;
    @NotBlank(message = "Verification code is required")
    @Size(min = 10, max = 64, message = "Verification code must be between 10 and 64 characters")
    private String verificationCode;
    private String transferStatus;
    @DecimalMin(value = "0.0", inclusive = false, message = "Cost must be greater than 0")
    private Double cost;
    private String transferDate;
    @Size(max = 500, message = "Transfer description must be less than 500 characters")
    private String transferDescription;

public BikeTransfer toEntity() {

    LocalDate parsedDate = LocalDate.now();
        if (transferDate != null && !transferDate.trim().isEmpty()) {
            try {
                parsedDate = LocalDate.parse(transferDate);
            } catch (Exception e) {
                log.error("Invalid transfer date format: {}", transferDate, e);
                throw new IllegalArgumentException("Invalid transfer date format");
            }
        }
        
        TransferStatus status = TransferStatus.PENDING;
        if (transferStatus != null && !transferStatus.trim().isEmpty()) {
            try {
                status = TransferStatus.valueOf(transferStatus.toUpperCase());
            } catch (IllegalArgumentException e) {
                status = TransferStatus.PENDING;
                log.warn("Invalid transfer status: {}, defaulting to PENDING", transferStatus);
                throw new IllegalArgumentException("Invalid transfer status");
            }
        }
        return BikeTransfer.builder()
                .bicycle(Bicycle.builder().id(bicycleId).build())
                .previousOwner(User.builder().id(previousOwnerId).build())
                .newOwner(User.builder().id(newOwnerId).build())
                .transferDate(parsedDate)
                .transferReason(transferReason)
                .verificationCode(verificationCode)
                .transferStatus(status)
                .cost(cost)
                .build();
    }
}
