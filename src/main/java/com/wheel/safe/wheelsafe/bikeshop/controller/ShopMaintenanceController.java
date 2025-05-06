package com.wheel.safe.wheelsafe.bikeshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceCreateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceResponseDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.MaintenanceUpdateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.service.BikeMaintenanceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author Lawrence Karanja
 * @Date 2023-10-01
 * 
 * @Description: This class is responsible for handling all the requests related to bike maintenance at a shop.
 */

@RestController
@RequestMapping("/api/bikeshop/maintenance")
@RequiredArgsConstructor
@Schema(description = "Controller for handling bike maintenance at a shop")
public class ShopMaintenanceController {
    private final BikeMaintenanceService bikeMaintenanceService;

    @PostMapping()
    @Operation(summary = "Create a new bike maintenance record", description = "Creates a new bike maintenance record in the system.")
    @ApiResponse(responseCode = "200", description = "Maintenance record created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<MaintenanceResponseDto> createMaintenance(@RequestBody MaintenanceCreateRequestDto maintenance) {
        MaintenanceResponseDto createdMaintenance = bikeMaintenanceService.createMaintenance(maintenance);
        return ResponseEntity.ok(createdMaintenance);
    }
    @PostMapping("/update")
    @Operation(summary = "Update an existing bike maintenance record", description = "Updates an existing bike maintenance record in the system.")
    @ApiResponse(responseCode = "200", description = "Maintenance record updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Maintenance record not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<MaintenanceResponseDto> updateMaintenance(@RequestBody MaintenanceUpdateRequestDto maintenance) {
        MaintenanceResponseDto updatedMaintenance = bikeMaintenanceService.updateMaintenance(maintenance);
        return ResponseEntity.ok(updatedMaintenance);
    }
    @PostMapping("/delete")
    @Operation(summary = "Delete a bike maintenance record", description = "Deletes a bike maintenance record from the system.")
    @ApiResponse(responseCode = "200", description = "Maintenance record deleted successfully")
    @ApiResponse(responseCode = "404", description = "Maintenance record not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteMaintenance(@RequestBody Long id) {
        bikeMaintenanceService.deleteMaintenance(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    @Operation(summary = "Get all bike maintenance records", description = "Retrieves all bike maintenance records from the system.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getAllMaintenances() {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }
    @GetMapping("/bikeshop")
    @Operation(summary = "Get bike maintenance records by bike shop ID", description = "Retrieves bike maintenance records for a specific bike shop.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified bike shop")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByBikeShopId(@RequestParam Long bikeShopId) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByBikeShopId(bikeShopId);
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/customer")
    @Operation(summary = "Get bike maintenance records by customer ID", description = "Retrieves bike maintenance records for a specific customer.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified customer")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByBicycleId(@RequestParam Long bicycleId) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByCustomerId(bicycleId);
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/{shopId}")
    @Operation(summary = "Get bike maintenance records by bike shop ID", description = "Retrieves bike maintenance records for a specific bike shop.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified bike shop")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByBikeShop(@PathVariable Long shopId) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByBikeShopId(shopId);
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bike maintenance record by ID", description = "Retrieves a specific bike maintenance record by its ID.")
    @ApiResponse(responseCode = "200", description = "Maintenance record retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Maintenance record not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<MaintenanceResponseDto> getMaintenance(@PathVariable Long id) {
        MaintenanceResponseDto maintenance = bikeMaintenanceService.getMaintenance(id);
        return ResponseEntity.ok(maintenance);
    }
    
    @GetMapping("/status")
    @Operation(summary = "Get bike maintenance records by status", description = "Retrieves bike maintenance records for a specific status.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified status")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByStatus(@RequestParam String status) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByStatus(status);
        return ResponseEntity.ok(maintenances);
    }
    @GetMapping("/bicycle")
    @Operation(summary = "Get bike maintenance records by bicycle Model", description = "Retrieves bike maintenance records for a specific bicycle model.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified bicycle model")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByBicycleModel(@RequestParam String bicycleModel) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByBikeModel(bicycleModel);
        return ResponseEntity.ok(maintenances);
    }
    
    @GetMapping("/{startDate}-{endDate}")
    @Operation(summary = "Get bike maintenance records by maintenance date range", description = "Retrieves bike maintenance records within a specific date range.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found within the specified date range")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByMaintenanceDateBetween(@PathVariable String startDate, @PathVariable String endDate) {
        
        Date startDateConverted = Date.from(LocalDateTime.parse(startDate).atZone(ZoneId.systemDefault()).toInstant());
        Date endDateConverted = Date.from(LocalDateTime.parse(endDate).atZone(ZoneId.systemDefault()).toInstant());
        
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByMaintenanceDateBetween(startDateConverted, endDateConverted);
        return ResponseEntity.ok(maintenances);
    }
    @GetMapping("/cost")
    @Operation(summary = "Get bike maintenance records by cost range", description = "Retrieves bike maintenance records within a specific cost range.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found within the specified cost range")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByCostBetween(@RequestParam Double minCost, @RequestParam Double maxCost) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByCostBetween(minCost, maxCost);
        return ResponseEntity.ok(maintenances);
    }
    @GetMapping("/serviceType")
    @Operation(summary = "Get bike maintenance records by service type", description = "Retrieves bike maintenance records for a specific service type.")
    @ApiResponse(responseCode = "200", description = "List of maintenance records retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No maintenance records found for the specified service type")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<MaintenanceResponseDto>> getMaintenancesByServiceType(@RequestParam String serviceType) {
        List<MaintenanceResponseDto> maintenances = bikeMaintenanceService.getMaintenancesByServiceType(serviceType);
        return ResponseEntity.ok(maintenances);
    }
    

}
