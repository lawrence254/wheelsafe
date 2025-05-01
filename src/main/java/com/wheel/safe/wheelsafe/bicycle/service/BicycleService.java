package com.wheel.safe.wheelsafe.bicycle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.exceptions.BicycleNotFoundException;
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
                .orElseThrow(() -> BicycleNotFoundException.withId(id));
    }

    public Bicycle getBicycleBySerialNumber(String serialNumber) {
        return bicycleRepository.findBySerialNumber(serialNumber)
                .orElseThrow(() -> BicycleNotFoundException.withSerialNumber(serialNumber));
    }

    public Bicycle getBicycleByBrand(String brand) {
        return bicycleRepository.findByBrand(brand)
                .orElseThrow(() -> BicycleNotFoundException.withBrand(brand));
    }

    public Bicycle getBicycleByModel(String model) {
        return bicycleRepository.findByModel(model)
                .orElseThrow(() -> BicycleNotFoundException.withModel(model));
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
        if (!bicycleRepository.existsById(id)) {
            throw BicycleNotFoundException.withId(id);
        }
        bicycleRepository.deleteById(id);
    }

    public Bicycle updateBicycle(Bicycle bicycle) {
        if (bicycle.getId() != null && !bicycleRepository.existsById(bicycle.getId())) {
            throw BicycleNotFoundException.withId(bicycle.getId());
        }
        return bicycleRepository.save(bicycle);
    }
}