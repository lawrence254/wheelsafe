package com.wheel.safe.wheelsafe.transfer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeTransferStatusUpdateDTO {
    @NotNull(message = "Transfer ID is required")
    private Long transferId;
    @NotBlank(message = "Transfer status is required")
    private String transferStatus;
    @Size(max = 100, message = "Verification code must be less than 100 characters")
    private String verificationCode; 
    private String reason;
}
