package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeShopAlreadyExistsException;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BikeShopNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.BikeShopRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeShopService {
    private final BikeShopRepository bikeShopRepository;
    
    
    public void addBikeShop(BikeShop bikeShop) {
        if (bikeShopRepository.existsById(bikeShop.getId())) {
            throw new BikeShopAlreadyExistsException("BikeShop with id " + bikeShop.getId() + " already exists");
        }
        bikeShopRepository.save(bikeShop);
    }

    public void updateBikeShop(BikeShop bikeShop) {
        bikeShopRepository.save(bikeShop);
    }

    public void deleteBikeShop(Long id) {
        bikeShopRepository.deleteById(id);
    }

    public BikeShop getBikeShop(Long id) {
        return bikeShopRepository.findById(id)
                .orElseThrow(() -> new BikeShopNotFoundException("BikeShop with id " + id + " not found"));
    }

    public List<BikeShop> getAllBikeShops() {
        return bikeShopRepository.findAll();
    }

    public List<BikeShop> getBikeShopsByName(String name) {
        List<BikeShop> bikeShops = bikeShopRepository.findByName(name);
        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found with name: " + name);
        }
        return bikeShops;
    }

    public List<BikeShop> getBikeShopsByLocation(String location) {
        List<BikeShop> bikeShops = bikeShopRepository.findByLocation(location);
        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found at location: " + location);
        }
        return bikeShops;
    }

    public List<BikeShop> getBikeShopsByService(String service) {
        List<BikeShop> bikeShops = bikeShopRepository.findByServicesContaining(service);
        if (bikeShops.isEmpty()) {
            throw new BikeShopNotFoundException("No BikeShops found offering service: " + service);
        }
        return bikeShops;
    }
}
