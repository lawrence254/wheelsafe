package com.wheel.safe.wheelsafe.bikeshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.bikeshop.dto.BikeShopRequest;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeShopResponse;
import com.wheel.safe.wheelsafe.bikeshop.service.BikeShopService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bikeshop")
@RequiredArgsConstructor
@Schema(description = "Bike Shop API")
@Tag(name = "BikeShop", description = "Bike shop management endpoints")
public class BikeShopController {
    private final BikeShopService bikeShopService;

    @PostMapping()
    @Operation(summary = "Create a BikeShop", description = "Create a new BikeShop")
    @ApiResponse(responseCode = "200", description = "BikeShop created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeShopResponse> createBikeShop(@Valid @RequestBody BikeShopRequest bikeShopRequest) {
        BikeShopResponse bikeShop = bikeShopService.addBikeShop(bikeShopRequest);
        return ResponseEntity.ok(bikeShop);
    }

    @PostMapping("/update")
    @Operation(summary = "Update a BikeShop", description = "Update an existing BikeShop")
    @ApiResponse(responseCode = "200", description = "BikeShop updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "BikeShop not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeShopResponse> updateBikeShop(@RequestBody BikeShopRequest bikeShop) {
        BikeShopResponse updatedBikeShop = bikeShopService.updateBikeShop(bikeShop);
        return ResponseEntity.ok(updatedBikeShop);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a BikeShop", description = "Delete an existing BikeShop")
    @ApiResponse(responseCode = "200", description = "BikeShop deleted successfully")
    @ApiResponse(responseCode = "404", description = "BikeShop not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteBikeShop(@RequestBody Long id) {
        bikeShopService.deleteBikeShop(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a BikeShop by ID", description = "Get a BikeShop by its ID")
    @ApiResponse(responseCode = "200", description = "BikeShop found")
    @ApiResponse(responseCode = "404", description = "BikeShop not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeShopResponse> getBikeShopById(@PathVariable Long id) {
        BikeShopResponse bikeShop = bikeShopService.getBikeShop(id);
        return ResponseEntity.ok(bikeShop);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all BikeShops", description = "Get all BikeShops")
    @ApiResponse(responseCode = "200", description = "List of BikeShops found")
    @ApiResponse(responseCode = "404", description = "No BikeShops found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeShopResponse>> getAllBikeShops() {
        List<BikeShopResponse> bikeShops = bikeShopService.getAllBikeShops();
        return ResponseEntity.ok(bikeShops);
    }

    @GetMapping("/name")
    @Operation(summary = "Get BikeShops by name", description = "Get BikeShops by their name")
    @ApiResponse(responseCode = "200", description = "List of BikeShops found")
    @ApiResponse(responseCode = "404", description = "No BikeShops found with the given name")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeShopResponse>> getBikeShopsByName(@RequestParam String name) {
        List<BikeShopResponse> bikeShops = bikeShopService.getBikeShopsByName(name);
        return ResponseEntity.ok(bikeShops);
    }

    @GetMapping("/location")
    @Operation(summary = "Get BikeShops by location", description = "Get BikeShops by their location")
    @ApiResponse(responseCode = "200", description = "List of BikeShops found")
    @ApiResponse(responseCode = "404", description = "No BikeShops found at the given location")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeShopResponse>> getBikeShopsByLocation(@RequestParam String location) {
        List<BikeShopResponse> bikeShops = bikeShopService.getBikeShopsByLocation(location);
        return ResponseEntity.ok(bikeShops);
    }

    @GetMapping("/service")
    @Operation(summary = "Get BikeShops by service", description = "Get BikeShops by the services they offer")
    @ApiResponse(responseCode = "200", description = "List of BikeShops found")
    @ApiResponse(responseCode = "404", description = "No BikeShops found offering the given service")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeShopResponse>> getBikeShopsByService(@RequestParam String service) {
        List<BikeShopResponse> bikeShops = bikeShopService.getBikeShopsByService(service);
        return ResponseEntity.ok(bikeShops);
    }

}
