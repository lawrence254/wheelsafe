package com.wheel.safe.wheelsafe.bicycle.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;

public class BicycleMapper {

    public static BicycleDTO toDto(Bicycle bicycle) {
        return BicycleDTO.builder()
                .id(bicycle.getId())
                .model(bicycle.getModel())
                .brand(bicycle.getBrand())
                .serialNumber(bicycle.getSerialNumber())
                .color(bicycle.getColor())
                .type(bicycle.getType())
                .size(bicycle.getSize())
                .frameMaterial(bicycle.getFrameMaterial())
                .gearSystem(bicycle.getGearSystem())
                .brakeType(bicycle.getBrakeType())
                .tireSize(bicycle.getTireSize())
                .accessories(bicycle.getAccessories())
                .build();
    }

    public static Bicycle toEntity(BicycleRequest request) {
        return Bicycle.builder()
                .model(request.getModel())
                .brand(request.getBrand())
                .serialNumber(request.getSerialNumber())
                .color(request.getColor())
                .type(request.getType())
                .size(request.getSize())
                .frameMaterial(request.getFrameMaterial())
                .gearSystem(request.getGearSystem())
                .brakeType(request.getBrakeType())
                .tireSize(request.getTireSize())
                .accessories(request.getAccessories())
                .build();
    }

    public static BicycleResponse toResponse(Bicycle bicycle) {
        return BicycleResponse.builder()
                .id(bicycle.getId())
                .model(bicycle.getModel())
                .brand(bicycle.getBrand())
                .serialNumber(bicycle.getSerialNumber())
                .color(bicycle.getColor())
                .type(bicycle.getType())
                .size(bicycle.getSize())
                .frameMaterial(bicycle.getFrameMaterial())
                .gearSystem(bicycle.getGearSystem())
                .brakeType(bicycle.getBrakeType())
                .tireSize(bicycle.getTireSize())
                .accessories(bicycle.getAccessories())
                .build();
    }
}
