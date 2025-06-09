package com.wheel.safe.wheelsafe.transfer.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.transfer.entity.BikeTransfer;
import com.wheel.safe.wheelsafe.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeTransferResponseDTO {
    private Long id;
    private Long bicycleId;
    private String model;
    private String brand;
    private String serialNumber;
    private Long previousOwnerId;
    private String previousOwnerFirstName;
    private String previousOwnerLastName;
    private String previousOwnerEmail;
    private Long newOwnerId;
    private String newOwnerFirstName;
    private String newOwnerLastName;
    private String newOwnerEmail;
    private String transferDate;
    private String transferReason;
    private String transferStatus;
    private Double cost;
    private String transferDescription;


    public static BikeTransferResponseDTO fromEntity(BikeTransfer bikeTransfer) {
        return BikeTransferResponseDTO.builder()
                .id(bikeTransfer.getId())
                .bicycleId(bikeTransfer.getBicycle().getId())
                .model(bikeTransfer.getBicycle().getModel())
                .brand(bikeTransfer.getBicycle().getBrand())
                .serialNumber(bikeTransfer.getBicycle().getSerialNumber())
                .previousOwnerId(bikeTransfer.getPreviousOwner().getId())
                .previousOwnerFirstName(bikeTransfer.getPreviousOwner().getFirstName())
                .previousOwnerLastName(bikeTransfer.getPreviousOwner().getLastName())
                .previousOwnerEmail(bikeTransfer.getPreviousOwner().getEmail())
                .newOwnerId(bikeTransfer.getNewOwner().getId())
                .newOwnerFirstName(bikeTransfer.getNewOwner().getFirstName())
                .newOwnerLastName(bikeTransfer.getNewOwner().getLastName())
                .newOwnerEmail(bikeTransfer.getNewOwner().getEmail())
                .transferDate(bikeTransfer.getTransferDate().toString())
                .transferStatus(bikeTransfer.getTransferStatus().name())
                .cost(bikeTransfer.getCost())
                .transferDescription(bikeTransfer.getTransferReason())
                .build();
    }

    public static BikeTransferResponseDTO summaryFromEntity(BikeTransfer bikeTransfer) {
        return BikeTransferResponseDTO.builder()
                .id(bikeTransfer.getId())
                .bicycleId(bikeTransfer.getBicycle().getId())
                .model(bikeTransfer.getBicycle().getModel())
                .brand(bikeTransfer.getBicycle().getBrand())
                .serialNumber(bikeTransfer.getBicycle().getSerialNumber())
                .previousOwnerId(bikeTransfer.getPreviousOwner().getId())
                .previousOwnerFirstName(bikeTransfer.getPreviousOwner().getFirstName())
                .previousOwnerLastName(bikeTransfer.getPreviousOwner().getLastName())
                .newOwnerId(bikeTransfer.getNewOwner().getId())
                .newOwnerFirstName(bikeTransfer.getNewOwner().getFirstName())
                .newOwnerLastName(bikeTransfer.getNewOwner().getLastName())
                .transferDate(bikeTransfer.getTransferDate().toString())
                .transferStatus(bikeTransfer.getTransferStatus().name())
                .build();
    }
    
}
