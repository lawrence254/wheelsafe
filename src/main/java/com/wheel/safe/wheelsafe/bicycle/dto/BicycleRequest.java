package com.wheel.safe.wheelsafe.bicycle.dto;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.entity.BicycleType;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BicycleRequest {
    private String model;
    private String brand;
    private String serialNumber;
    private String color;
    private BicycleType type;
    private String size;
    private String frameMaterial;
    private String gearSystem;
    private String brakeType;
    private String tireSize;
    private String accessories;
    private Long shopId; 

    // Convert BicycleRequest to Bicycle entity
    public Bicycle toEntity() {
        Bicycle bicycle = new Bicycle();
        bicycle.setModel(this.model);
        bicycle.setBrand(this.brand);
        bicycle.setSerialNumber(this.serialNumber);
        bicycle.setColor(this.color);
        bicycle.setType(BicycleType.valueOf(this.type.name()));
        bicycle.setSize(this.size);
        bicycle.setFrameMaterial(this.frameMaterial);
        bicycle.setGearSystem(this.gearSystem);
        bicycle.setBrakeType(this.brakeType);
        bicycle.setTireSize(this.tireSize);
        bicycle.setAccessories(this.accessories);

        if( this.shopId != null) {
            BikeShop shop = new BikeShop();
            shop.setId(this.shopId);
            bicycle.setShopId(shop);
        }
        
        return bicycle;
    }
}
