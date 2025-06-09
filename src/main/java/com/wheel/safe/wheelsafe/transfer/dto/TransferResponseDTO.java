package com.wheel.safe.wheelsafe.transfer.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferResponseDTO {
    private Long id;
    private Bicycle bicycle;
    private User previousOwner;
    private User newOwner;
    private String transferDate;
    private String transferReason;
    private String verificationCode;
    private String transferStatus;
    private Double cost;
    private String transferDescription;
    
}
