package com.wheel.safe.wheelsafe.bikeshop.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.repository.BicycleRepository;
import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceCreateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceResponseDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceUpdateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.entity.Maintenance;
import com.wheel.safe.wheelsafe.bikeshop.entity.MaintenanceStatus;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.BicycleNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.exceptions.MaintenanceNotFoundException;
import com.wheel.safe.wheelsafe.bikeshop.repository.MaintenanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BikeMaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final BicycleRepository bicycleRepository;

    public MaintenanceResponseDto createMaintenance(MaintenanceCreateRequestDto request) {
        Maintenance maintenance = request.toEntity();
        Bicycle bike = bicycleRepository.findById(request.getBikeId())
            .orElseThrow(() -> new BicycleNotFoundException("Bicycle not found with ID: " + request.getBikeId()));
        
        request.setBikeId(bike.getId());

        Maintenance newRecord = maintenanceRepository.save(maintenance);

        return MaintenanceResponseDto.fromEntity(newRecord);
    }
    public MaintenanceResponseDto updateMaintenance(MaintenanceUpdateRequestDto request) {
        Maintenance existingMaintenance = maintenanceRepository.findById(request.getId())
                .orElseThrow(() -> new MaintenanceNotFoundException(
                        "Maintenance with ID " + request.getId() + " not found."));
        return MaintenanceResponseDto.fromEntity(
                maintenanceRepository.save(request.toEntity(existingMaintenance)));

    }
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }
    public MaintenanceResponseDto getMaintenance(Long id) {
        return MaintenanceResponseDto.fromEntity(
                maintenanceRepository.findById(id)
                        .orElseThrow(() -> new MaintenanceNotFoundException(
                                "Maintenance with ID " + id + " not found.")));
    }
    public List<MaintenanceResponseDto> getAllMaintenances() {
        return maintenanceRepository.findAll().stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByBikeShopId(Long bikeShopId) {
        return maintenanceRepository.findByBikeShopId(bikeShopId).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByCustomerId(Long customerId) {
        return maintenanceRepository.findByCustomerId(customerId).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByStatus(String status) {
        return maintenanceRepository.findByStatus(MaintenanceStatus.valueOf(status)).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByBikeModel(String bikeModel) {
        return maintenanceRepository.findByBikeModel(bikeModel).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByMaintenanceDateBetween(Date startDate, Date endDate) {
        return maintenanceRepository.findByMaintenanceDateBetween(startDate, endDate).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByCostBetween(Double minCost, Double maxCost) {
        return maintenanceRepository.findByCostBetween(minCost, maxCost).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
    public List<MaintenanceResponseDto> getMaintenancesByServiceType(String serviceType) {
        return maintenanceRepository.findByServiceType(serviceType).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }

    public List<MaintenanceResponseDto> getMaintenanceByShopId(Long bikeShopId) {
        return maintenanceRepository.findByBikeShopId(bikeShopId).stream()
                .map(MaintenanceResponseDto::fromEntity)
                .toList();
    }
}
