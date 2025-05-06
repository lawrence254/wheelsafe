package com.wheel.safe.wheelsafe.bikeshop.dto;

import com.beust.jcommander.internal.Nullable;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BikeShopRequest {
    @Nullable
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String openingHours;
    private String services;
    private String latitude;
    private String longitude;
    private String imageUrl;
    private String description;

    public BikeShop toEntity() {
        BikeShop bikeShop = new BikeShop();
        bikeShop.setName(this.name);
        bikeShop.setAddress(this.address);
        bikeShop.setPhoneNumber(this.phoneNumber);
        bikeShop.setEmail(this.email);
        bikeShop.setWebsite(this.website);
        bikeShop.setOpeningHours(this.openingHours);
        bikeShop.setServices(this.services);
        bikeShop.setLatitude(this.latitude);
        bikeShop.setLongitude(this.longitude);
        bikeShop.setImageUrl(this.imageUrl);
        bikeShop.setDescription(this.description);

        return bikeShop;
    }

    public static BikeShopRequest fromEntity(BikeShop bikeShop) {
        return BikeShopRequest.builder()
                .name(bikeShop.getName())
                .address(bikeShop.getAddress())
                .phoneNumber(bikeShop.getPhoneNumber())
                .email(bikeShop.getEmail())
                .website(bikeShop.getWebsite())
                .openingHours(bikeShop.getOpeningHours())
                .services(bikeShop.getServices())
                .latitude(bikeShop.getLatitude())
                .longitude(bikeShop.getLongitude())
                .imageUrl(bikeShop.getImageUrl())
                .description(bikeShop.getDescription())
                .build();
    }
}
