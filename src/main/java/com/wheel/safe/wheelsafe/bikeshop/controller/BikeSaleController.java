package com.wheel.safe.wheelsafe.bikeshop.controller;
/*
 * @Author: Lawrence Karanja
 * 
 * @Date: 2023-10-01
 * 
 * @Description: This class will handle the bike sale operations such as creating, updating, deleting, and retrieving bike sales.
 */

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleCreateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleResponseDto;
import com.wheel.safe.wheelsafe.bikeshop.dto.BikeSaleUpdateRequestDto;
import com.wheel.safe.wheelsafe.bikeshop.entity.SaleStatus;
import com.wheel.safe.wheelsafe.bikeshop.service.BikeSaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bikeshop/sales")
@RequiredArgsConstructor
@Schema(description = "Bike Sale API")
public class BikeSaleController {
    private final BikeSaleService bikeSaleService;

    @PostMapping()
    @Operation(summary = "Create a Bike Sale", description = "Create a new Bike Sale")
    @ApiResponse(responseCode = "200", description = "Bike Sale created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeSaleResponseDto> createBikeSale(@RequestBody BikeSaleCreateRequestDto bikeSaleRequest) {
        BikeSaleResponseDto bikeSale = bikeSaleService.createSale(bikeSaleRequest);
        return ResponseEntity.ok(bikeSale);
    }

    @PostMapping("/update")
    @Operation(summary = "Update a Bike Sale", description = "Update an existing Bike Sale")
    @ApiResponse(responseCode = "200", description = "Bike Sale updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Bike Sale not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeSaleResponseDto> updateBikeSale(@RequestBody BikeSaleUpdateRequestDto bikeSale) {
        BikeSaleResponseDto updatedBikeSale = bikeSaleService.updateSale(bikeSale);
        return ResponseEntity.ok(updatedBikeSale);
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete a Bike Sale", description = "Delete an existing Bike Sale")
    @ApiResponse(responseCode = "200", description = "Bike Sale deleted successfully")
    @ApiResponse(responseCode = "404", description = "Bike Sale not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Void> deleteBikeSale(@RequestBody Long id) {
        bikeSaleService.deleteSale(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Bike Sale", description = "Get an existing Bike Sale")
    @ApiResponse(responseCode = "200", description = "Bike Sale retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Bike Sale not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<BikeSaleResponseDto> getBikeSale(@PathVariable Long id) {
        BikeSaleResponseDto bikeSale = bikeSaleService.getSale(id);
        return ResponseEntity.ok(bikeSale);
    }
    @GetMapping("/all")
    @Operation(summary = "Get all Bike Sales", description = "Get all existing Bike Sales")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getAllBikeSales() {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getAllSales();
        return ResponseEntity.ok(bikeSales);
    }

    @GetMapping("/shop/{bikeShopId}")
    @Operation(summary = "Get Bike Sales by Bike Shop ID", description = "Get all Bike Sales for a specific Bike Shop")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Bike Shop")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesByBikeShopId(@PathVariable Long bikeShopId) {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesByBikeShopId(bikeShopId);
        return ResponseEntity.ok(bikeSales);
    }
    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get Bike Sales by Customer ID", description = "Get all Bike Sales for a specific Customer")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Customer")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesByCustomerId(@PathVariable Long customerId) {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesByCustomerId(customerId);
        return ResponseEntity.ok(bikeSales);
    }
    @GetMapping("/status/{status}")
    @Operation(summary = "Get Bike Sales by Status", description = "Get all Bike Sales for a specific Status")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Status")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesByStatus(@PathVariable String status) {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesByStatus(SaleStatus.valueOf(status));
        return ResponseEntity.ok(bikeSales);
    }

    @GetMapping("/model/{bikeModel}")
    @Operation(summary = "Get Bike Sales by Bike Model", description = "Get all Bike Sales for a specific Bike Model")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Bike Model")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesByBikeModel(@PathVariable String bikeModel) {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesByBikeModel(bikeModel);
        return ResponseEntity.ok(bikeSales);
    }
    @GetMapping("/date/{startDate}-{endDate}")
    @Operation(summary = "Get Bike Sales by Sale Date", description = "Get all Bike Sales for a specific Sale Date")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Sale Date")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesBySaleDate(@PathVariable String startDate, @PathVariable String endDate) {
        // Convert the date strings to Date objects
        Date startDateConverted = Date.from(LocalDateTime.parse(startDate).atZone(ZoneId.systemDefault()).toInstant());
        Date endDateConverted = Date.from(LocalDateTime.parse(endDate).atZone(ZoneId.systemDefault()).toInstant());
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesBySaleDateBetween(startDateConverted, endDateConverted);
        return ResponseEntity.ok(bikeSales);
    }

    @GetMapping("/price/{minPrice}-{maxPrice}")
    @Operation(summary = "Get Bike Sales by Sale Price", description = "Get all Bike Sales for a specific Sale Price")
    @ApiResponse(responseCode = "200", description = "Bike Sales retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No Bike Sales found for the specified Sale Price")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BikeSaleResponseDto>> getBikeSalesBySalePrice(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        List<BikeSaleResponseDto> bikeSales = bikeSaleService.getSalesBySalePriceBetween(minPrice, maxPrice);
        return ResponseEntity.ok(bikeSales);
    }
    
}
