package com.wheel.safe.wheelsafe.bicycle.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.entity.BicycleType;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;

public class BicycleMapper {

    public static BicycleDTO toDto(Bicycle bicycle) {
        return BicycleDTO.builder()
            .id(bicycle.getId())
            .model(bicycle.getModel())
            .brand(bicycle.getBrand())
            .shopId(bicycle.getShopId() != null ? bicycle.getShopId().getId() : null)
            .serialNumber(bicycle.getSerialNumber())
            .color(bicycle.getColor())
            .type(bicycle.getType().name())
            .size(bicycle.getSize())
            .frameMaterial(bicycle.getFrameMaterial())
            .gearSystem(bicycle.getGearSystem())
            .brakeType(bicycle.getBrakeType())
            .tireSize(bicycle.getTireSize())
            .accessories(bicycle.getAccessories())
            .build();
    }

    public static Bicycle toEntity(BicycleRequest request) {
        Bicycle bicycle= Bicycle.builder()
                .model(request.getModel())
                .brand(request.getBrand())
                .serialNumber(request.getSerialNumber())
                .color(request.getColor())
                .type(BicycleType.valueOf(request.getType().name()))
                .size(request.getSize())
                .frameMaterial(request.getFrameMaterial())
                .gearSystem(request.getGearSystem())
                .brakeType(request.getBrakeType())
                .tireSize(request.getTireSize())
                .accessories(request.getAccessories())
                .build();

                if( request.getShopId() != null) {
                    BikeShop shop = new BikeShop();
                    shop.setId(request.getShopId());
                    bicycle.setShopId(shop);
                }
                return bicycle;
    }

    public static BicycleResponse toResponse(Bicycle bicycle) {
        return BicycleResponse.builder()
                .id(bicycle.getId())
                .shopId(bicycle.getShopId() != null ? bicycle.getShopId().getId() : null)
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
