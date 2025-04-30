package com.wheel.safe.wheelsafe.bicycle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;

public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
    Optional<Bicycle> findByBrand(String brand);
    Optional<Bicycle> findByModel(String model);
    Optional<Bicycle> findBySerialNumber(String serialNumber);
}