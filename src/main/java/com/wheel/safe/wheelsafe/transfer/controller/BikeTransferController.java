package com.wheel.safe.wheelsafe.transfer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.transfer.dto.BikeTransferRequestDTO;
import com.wheel.safe.wheelsafe.transfer.dto.BikeTransferResponseDTO;
import com.wheel.safe.wheelsafe.transfer.service.BikeTransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/*
 * BikeTransferController.java
 * @Author: Lawrence Karanja
 * @GitHub: @lawrence254
 * @Date: 2023-10-01
 * @Description: Controller class for handling bike transfer requests.
 */

@RestController
@RequestMapping("/api/transfers")
@RequiredArgsConstructor
@Schema(description = "Controller for managing bike transfers")
public class BikeTransferController {

    private final BikeTransferService bikeTransferService;

    @PostMapping()
    @Operation(summary = "Create a new bike transfer",
            description = "Creates a new bike transfer record with the provided details.")
    @ApiResponse(
            responseCode = "200",
            description = "Bike transfer created successfully")
    @ApiResponse(
            responseCode = "400",
            description = "Invalid request data")
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<BikeTransferResponseDTO> createTransfer(
            @RequestBody BikeTransferRequestDTO request) {
        BikeTransferResponseDTO response = bikeTransferService.createTransfer(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{transferId}")
    @Operation(summary = "Get bike transfer by ID",
            description = "Retrieves a bike transfer record by its ID.")
    @ApiResponse(
            responseCode = "200",
            description = "Bike transfer retrieved successfully")
    @ApiResponse(
            responseCode = "404",
            description = "Bike transfer not found")
    public ResponseEntity<BikeTransferResponseDTO> getTransferById(@PathVariable Long transferId) {
        BikeTransferResponseDTO response = bikeTransferService.getTransferById(transferId);
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    @Operation(summary = "Get all bike transfers",
            description = "Retrieves a list of all bike transfers.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers retrieved successfully")
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<BikeTransferResponseDTO>> getAllTransfers() {
        List<BikeTransferResponseDTO> response = bikeTransferService.getAllTransfers();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/status/{status}")
    @Operation(summary = "Get bike transfers by status",
            description = "Retrieves a list of bike transfers filtered by their status.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers by status retrieved successfully")
    @ApiResponse(
            responseCode = "404",
            description = "No bike transfers found for the given status")
    public ResponseEntity<List<BikeTransferResponseDTO>> getTransfersByStatus(@PathVariable String status) {
        List<BikeTransferResponseDTO> response = bikeTransferService.getTransfersByStatus(status);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bicycle/{bicycleId}")
    @Operation(summary = "Get bike transfers by bicycle ID",
            description = "Retrieves a list of bike transfers associated with a specific bicycle ID.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers by bicycle ID retrieved successfully")
    @ApiResponse(
            responseCode = "404",
            description = "No bike transfers found for the given bicycle ID")
            @ApiResponse(
            responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<BikeTransferResponseDTO>> getTransfersByBicycleId(@PathVariable Long bicycleId) {
        List<BikeTransferResponseDTO> response = bikeTransferService.getTransfersByBicycleId(bicycleId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/previous-owner/{previousOwnerId}")
    @Operation(summary = "Get bike transfers by previous owner ID",
            description = "Retrieves a list of bike transfers associated with a specific previous owner ID.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers by previous owner ID retrieved successfully")
    @ApiResponse(
            responseCode = "404",
            description = "No bike transfers found for the given previous owner ID")
    public ResponseEntity<List<BikeTransferResponseDTO>> getTransfersByPreviousOwnerId(@PathVariable Long previousOwnerId) {
        List<BikeTransferResponseDTO> response = bikeTransferService.getTransfersByPreviousOwnerId(previousOwnerId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/new-owner/{newOwnerId}")
    @Operation(summary = "Get bike transfers by new owner ID",
            description = "Retrieves a list of bike transfers associated with a specific new owner ID.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers by new owner ID retrieved successfully")
    @ApiResponse(
            responseCode = "404",
            description = "No bike transfers found for the given new owner ID")
    public ResponseEntity<List<BikeTransferResponseDTO>> getTransfersByNewOwnerId(@PathVariable Long newOwnerId) {
        List<BikeTransferResponseDTO> response = bikeTransferService.getTransfersByNewOwnerId(newOwnerId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{transferId}/status/{status}")
    @Operation(summary = "Update bike transfer status",
            description = "Updates the status of a bike transfer record by its ID.")
    @ApiResponse(
            responseCode = "200",
            description = "Bike transfer status updated successfully")
    @ApiResponse(
            responseCode = "404",
            description = "Bike transfer not found")
    public ResponseEntity<BikeTransferResponseDTO> updateTransferStatus(
            @PathVariable Long transferId,
            @PathVariable String status) {
        BikeTransferResponseDTO response = bikeTransferService.updateTransferStatus(transferId, status);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/{transferId}/delete")
    @Operation(summary = "Delete bike transfer",
            description = "Deletes a bike transfer record by its ID.")
    @ApiResponse(
            responseCode = "200",
            description = "Bike transfer deleted successfully")
    @ApiResponse(
            responseCode = "404",
            description = "Bike transfer not found")
    public ResponseEntity<Void> deleteTransfer(@PathVariable Long transferId) {
        bikeTransferService.deleteTransfer(transferId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    @Operation(summary = "Search bike transfers",
            description = "Searches for bike transfers based on various criteria.")
    @ApiResponse(
            responseCode = "200",
            description = "List of bike transfers matching the search criteria retrieved successfully")
    @ApiResponse(
            responseCode = "400",
            description = "Invalid search parameters")
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error")
    public ResponseEntity<List<BikeTransferResponseDTO>> searchTransfers(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long bicycleId,
            @RequestParam(required = false) Long previousOwnerId,
            @RequestParam(required = false) Long newOwnerId) {
        List<BikeTransferResponseDTO> response = bikeTransferService.searchTransfers(status, bicycleId, previousOwnerId, newOwnerId);
        return ResponseEntity.ok(response);
    }

}
