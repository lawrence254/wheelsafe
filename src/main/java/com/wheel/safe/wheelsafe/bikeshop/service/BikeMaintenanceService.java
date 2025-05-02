package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.MaintenanceNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.MaintenanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeMaintenanceService {
    private final MaintenanceRepository maintenanceRepository;

    public Maintenance createMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }
    public Maintenance updateMaintenance(Maintenance maintenance) {
        return maintenanceRepository.save(maintenance);
    }
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }
    public Maintenance getMaintenance(Long id) {
        return maintenanceRepository.findById(id)
            .orElseThrow(() -> new MaintenanceNotFoundException("Maintenance with ID " + id + " not found."));
    }
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }
    public List<Maintenance> getMaintenancesByBikeShopId(Long bikeShopId) {
        return maintenanceRepository.findByBikeShopId(bikeShopId);
    }
    public List<Maintenance> getMaintenancesByCustomerId(Long customerId) {
        return maintenanceRepository.findByCustomerId(customerId);
    }
    public List<Maintenance> getMaintenancesByStatus(String status) {
        return maintenanceRepository.findByStatus(MaintenanceStatus.valueOf(status));
    }
    public List<Maintenance> getMaintenancesByBikeModel(String bikeModel) {
        return maintenanceRepository.findByBikeModel(bikeModel);
    }
    public List<Maintenance> getMaintenancesByMaintenanceDateBetween(Date startDate, Date endDate) {
        return maintenanceRepository.findByMaintenanceDateBetween(startDate, endDate);
    }
    public List<Maintenance> getMaintenancesByCostBetween(Double minCost, Double maxCost) {
        return maintenanceRepository.findByCostBetween(minCost, maxCost);
    }
    public List<Maintenance> getMaintenancesByServiceType(String serviceType) {
        return maintenanceRepository.findByServiceType(serviceType);
    }
}
