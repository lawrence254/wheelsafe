package com.wheel.safe.wheelsafe.bikeshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByBikeShopId(Long bikeShopId);
    List<Maintenance> findByCustomerId(Long customerId);
    List<Maintenance> findByStatus(MaintenanceStatus status);
    List<Maintenance> findByBikeModel(String bikeModel);
    List<Maintenance> findByMaintenanceDateBetween(Date startDate, Date endDate);
    List<Maintenance> findByCostBetween(Double minCost, Double maxCost);
    List<Maintenance> findByServiceType(String serviceType);
    List<Maintenance> findByServiceDateBetween(Date startDate, Date endDate);

}
