package com.wheel.safe.wheelsafe.bicycle.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.BicycleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BicycleResponse {
    private Long id;
    private String model;
    private String brand;
    private Long shopId;
    private String serialNumber;
    private String color;
    private BicycleType type;
    private String size;
    private String frameMaterial;
    private String gearSystem;
    private String brakeType;
    private String tireSize;
    private String accessories;
    private String qrCodeUrl;
}
