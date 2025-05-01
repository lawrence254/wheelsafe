package com.wheel.safe.wheelsafe.bicycle.dto;

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
    private String serialNumber;
    private String color;
    private String type;
    private String size;
    private String frameMaterial;
    private String gearSystem;
    private String brakeType;
    private String tireSize;
    private String accessories;
    private String qrCodeUrl;
}
