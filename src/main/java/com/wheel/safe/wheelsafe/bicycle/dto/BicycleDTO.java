package com.wheel.safe.wheelsafe.bicycle.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BicycleDTO {
    private Long id;
    @NotBlank(message = "Model is required")
    private String model;
    @NotBlank(message = "Brand is required")
    private String brand;
    @NotBlank(message = "Serial number is required")
    private String serialNumber;
    private String color;
    private String type;
    private String size;
    private String frameMaterial;
    private String gearSystem;
    private String brakeType;
    private String tireSize;
    private String accessories;
    private Long shopId;
}
