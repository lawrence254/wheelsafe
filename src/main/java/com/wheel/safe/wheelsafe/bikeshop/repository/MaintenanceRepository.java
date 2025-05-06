package com.wheel.safe.wheelsafe.bikeshop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceType;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByBikeShopId(Long bikeShopId);
    List<Maintenance> findByCustomerId(Long customerId);
    List<Maintenance> findByMaintenanceStatus(MaintenanceStatus maintenanceStatus);
    List<Maintenance> findByMaintenanceDateBetween(Date startDate, Date endDate);
    List<Maintenance> findByMaintenanceCostBetween(Double minCost, Double maxCost);
    List<Maintenance> findByMaintenanceType(MaintenanceType maintenanceType);

}
