package com.wheel.safe.wheelsafe.bikeshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.bikeshop.entity.BikeShop;

public interface BikeShopRepository extends JpaRepository<BikeShop, Long> {
    List<BikeShop> findByName(String name);
    List<BikeShop> findByAddress(String address);
    List<BikeShop> findByServicesContaining(String service);
    
}
