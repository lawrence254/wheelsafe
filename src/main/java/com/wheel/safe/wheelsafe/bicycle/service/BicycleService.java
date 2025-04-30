package com.wheel.safe.wheelsafe.bicycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.repository.BicycleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BicycleService {
    private final BicycleRepository bicycleRepository;
    
    public Bicycle addBicycle(Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }
    public Bicycle getBicycleById(Long id) {
        return bicycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bicycle not found with ID: " + id));
    }
    public Bicycle getBicycleBySerialNumber(String serialNumber) {
        return bicycleRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> new RuntimeException("Bicycle not found with Serial Number: " + serialNumber));
    }

    public Bicycle getBicycleByBrand(String brand) {
        return bicycleRepository.findByBrand(brand)
                .orElseThrow(() -> new RuntimeException("Bicycle not found with Brand: " + brand));
    }
    public Bicycle getBicycleByModel(String model) {
        return bicycleRepository.findByModel(model)
                .orElseThrow(() -> new RuntimeException("Bicycle not found with Model: " + model));
    }
    public List<Bicycle> getAllBicycles() {
        return bicycleRepository.findAll();
    }
    public Optional<Bicycle> getBicyclesByBrand(String brand) {
        return bicycleRepository.findByBrand(brand);
    }
    public Optional<Bicycle> getBicyclesByModel(String model) {
        return bicycleRepository.findByModel(model);
    }

    public void deleteBicycle(Long id) {
        bicycleRepository.deleteById(id);
    }
    public Bicycle updateBicycle(Bicycle bicycle) {
        return bicycleRepository.save(bicycle);
    }
}

