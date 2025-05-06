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
        return BikeShop.builder()
                .name(this.name)
                .address(this.address)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .website(this.website)
                .openingHours(this.openingHours)
                .services(this.services)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .imageUrl(this.imageUrl)
                .description(this.description)
                .build();
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
