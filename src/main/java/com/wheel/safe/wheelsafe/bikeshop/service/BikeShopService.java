package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bikeshop.dto.BikeShopRequest;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeShopResponse;
import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeShopNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.BikeShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeShopService {
    private final BikeShopRepository bikeShopRepository;
    
    
    public BikeShopResponse addBikeShop(BikeShopRequest bikeShopRequest) {
        
        BikeShop bikeShop = bikeShopRequest.toEntity();
        BikeShop savedBikeShop = bikeShopRepository.save(bikeShop);
        return BikeShopResponse.fromEntity(savedBikeShop);
    }

    public BikeShopResponse updateBikeShop(BikeShopRequest bikeShopRequest) {
        BikeShop existingBikeShop = bikeShopRepository.findById(bikeShopRequest.getId())
                .orElseThrow(() -> new BikeShopNotFoundException("BikeShop with id " + bikeShopRequest.getId() + " not found"));
        existingBikeShop= bikeShopRequest.toEntity();
        BikeShop updatedBikeShop = bikeShopRepository.save(existingBikeShop);
        return BikeShopResponse.fromEntity(updatedBikeShop);
    }

    public void deleteBikeShop(Long id) {
        bikeShopRepository.deleteById(id);
    }

    public BikeShopResponse getBikeShop(Long id) {
        return BikeShopResponse.fromEntity(bikeShopRepository.findById(id)
                .orElseThrow(() -> new BikeShopNotFoundException("BikeShop with id " + id + " not found")));
    }

    public List<BikeShopResponse> getAllBikeShops() {
        return bikeShopRepository.findAll().stream()
                .map(BikeShopResponse::fromEntity)
                .toList();
    }

    public List<BikeShopResponse> getBikeShopsByName(String name) {
        List<BikeShopResponse> bikeShops = bikeShopRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(BikeShopResponse::fromEntity)
                .toList();

        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found with name: " + name);
        }

        return bikeShops;
    }



    public List<BikeShopResponse> getBikeShopsByLocation(String location) {
        List<BikeShop> bikeShops = bikeShopRepository.findByAddressContainingIgnoreCase(location);
        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found at location: " + location);
        }
        return bikeShops.stream()
                .map(BikeShopResponse::fromEntity)
                .toList();
    }

    public List<BikeShopResponse> getBikeShopsByService(String service) {
        List<BikeShop> bikeShops = bikeShopRepository.findByServicesContainingIgnoreCase(service);
        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found offering service: " + service);
        }
        return bikeShops.stream()
                .map(BikeShopResponse::fromEntity)
                .toList();
    }
}
