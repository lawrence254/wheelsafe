package com.wheel.safe.wheelsafe.bicycle.controller;

/**
 * @Author: Lawrence Karanja
 * @Date: 2023/10/12
 * @Description: This class is responsible for handling HTTP requests related to bicycles.
 * It will contain methods to manage bicycle data, including creating, updating, deleting, and retrieving bicycle information.
 * It will also handle any necessary validation and error handling for the requests.
 */

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleMapper;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleRequest;
import com.wheel.safe.wheelsafe.bicycle.dto.BicycleResponse;
import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.bicycle.service.BicycleService;
import com.wheel.safe.wheelsafe.utilities.QRCodeGenerator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bicycle")
@RequiredArgsConstructor
@Schema(description = "Controller for managing bicycles")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Bicycle", description = "Operations related to bicycles")
public class BicycleController {
    private final BicycleService bicycleService;

    /**
     * Create a new bicycle
     * 
     * @param request The bicycle data
     * @return ResponseEntity containing the created bicycle response
     */
    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Create a new bicycle", description = "Creates a new bicycle and generates a QR code for it.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Bicycle created successfully")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<BicycleResponse> createBicycle(@Valid @RequestBody BicycleRequest request) {
        try {
            Bicycle bicycle = BicycleMapper.toEntity(request);
            Bicycle savedBicycle = bicycleService.addBicycle(bicycle);

            String qrCodeString = QRCodeGenerator.generateQRCode(BicycleMapper.toDto(savedBicycle));

            // Create response with QR code URL
            BicycleResponse response = BicycleMapper.toResponse(savedBicycle);
            response.setQrCodeUrl(qrCodeString);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all bicycles
     * 
     * @return ResponseEntity containing list of all bicycles
     */
    @GetMapping
    @Operation(summary = "Get all bicycles", description = "Retrieves all bicycles in the system")
    @ApiResponse(responseCode = "200", description = "List of bicycles retrieved successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<List<BicycleResponse>> getAllBicycles() {
        List<Bicycle> bicycles = bicycleService.getAllBicycles();
        List<BicycleResponse> responses = bicycles.stream()
                .map(BicycleMapper::toResponse)
                .toList();

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    /**
     * Get a bicycle by ID
     * 
     * @param id The bicycle ID
     * @return ResponseEntity containing the bicycle response
     * @throws WriterException
     */
    @Operation(summary = "Get bicycle by ID", description = "Retrieves a bicycle by its database ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bicycle found"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BicycleResponse> getBicycleById(
            @Parameter(description = "ID of the bicycle") @PathVariable Long id) throws WriterException {
        try {
            Bicycle bicycle = bicycleService.getBicycleById(id);
            BicycleResponse response = BicycleMapper.toResponse(bicycle);

            String qrCodeString = QRCodeGenerator.generateQRCode(BicycleMapper.toDto(bicycle));

            response.setQrCodeUrl(qrCodeString);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get a bicycle by serial number
     * 
     * @param serialNumber The bicycle serial number
     * @return ResponseEntity containing the bicycle response
     */
    @GetMapping("/serial/{serialNumber}")
    @Operation(summary = "Get bicycle by serial number", description = "Retrieves a bicycle by its serial number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bicycle found"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<BicycleResponse> getBicycleBySerialNumber(
            @Parameter(description = "Serial number of the bicycle") @PathVariable String serialNumber) {
        try {
            Bicycle bicycle = bicycleService.getBicycleBySerialNumber(serialNumber);
            BicycleResponse response = BicycleMapper.toResponse(bicycle);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get bicycles by brand
     * 
     * @param brand The bicycle brand
     * @return ResponseEntity containing the bicycle response
     */
    @GetMapping("/brand/{brand}")
    @Operation(summary = "Get bicycle by brand", description = "Retrieves a bicycle by brand")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bicycle found"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<BicycleResponse> getBicycleByBrand(
            @Parameter(description = "Brand name") @PathVariable String brand) {
        try {
            Bicycle bicycle = bicycleService.getBicycleByBrand(brand);
            BicycleResponse response = BicycleMapper.toResponse(bicycle);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get bicycles by model
     * 
     * @param model The bicycle model
     * @return ResponseEntity containing the bicycle response
     */
    @GetMapping("/model/{model}")
    @Operation(summary = "Get bicycle by model", description = "Retrieves a bicycle by model")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bicycle found"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<BicycleResponse> getBicycleByModel(
            @Parameter(description = "Model name") @PathVariable String model) {
        try {
            Bicycle bicycle = bicycleService.getBicycleByModel(model);
            BicycleResponse response = BicycleMapper.toResponse(bicycle);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update a bicycle
     * 
     * @param id      The bicycle ID
     * @param request The updated bicycle data
     * @return ResponseEntity containing the updated bicycle response
     * @throws WriterException
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a bicycle", description = "Updates an existing bicycle by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bicycle updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<BicycleResponse> updateBicycle(
            @Parameter(description = "ID of the bicycle") @PathVariable Long id,
            @Valid @RequestBody BicycleRequest request) throws WriterException {
        try {
            // Get existing bicycle
            Bicycle existingBicycle = bicycleService.getBicycleById(id);

            // Update fields
            existingBicycle.setModel(request.getModel());
            existingBicycle.setBrand(request.getBrand());
            existingBicycle.setSerialNumber(request.getSerialNumber());
            existingBicycle.setColor(request.getColor());
            existingBicycle.setType(request.getType());
            existingBicycle.setSize(request.getSize());
            existingBicycle.setFrameMaterial(request.getFrameMaterial());
            existingBicycle.setGearSystem(request.getGearSystem());
            existingBicycle.setBrakeType(request.getBrakeType());
            existingBicycle.setTireSize(request.getTireSize());
            existingBicycle.setAccessories(request.getAccessories());

            // Save updated bicycle
            Bicycle updatedBicycle = bicycleService.updateBicycle(existingBicycle);
            BicycleResponse response = BicycleMapper.toResponse(updatedBicycle);

            // Generate new QR code

            String qr = QRCodeGenerator.generateQRCode(BicycleMapper.toDto(updatedBicycle));
            response.setQrCodeUrl(qr);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a bicycle
     * 
     * @param id The bicycle ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a bicycle", description = "Deletes a bicycle by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bicycle deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<Void> deleteBicycle(@Parameter(description = "ID of the bicycle") @PathVariable Long id) {
        try {
            // Verify bicycle exists
            bicycleService.getBicycleById(id);

            // Delete bicycle
            bicycleService.deleteBicycle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Generate QR code for a bicycle
     * 
     * @param id The bicycle ID
     * @return ResponseEntity containing the QR code as base64 string
     * @throws WriterException
     */
    @GetMapping("/{id}/qrcode")
    @Operation(summary = "Generate bicycle QR code", description = "Generates a base64 QR code representation for a bicycle")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "QR code generated successfully"),
            @ApiResponse(responseCode = "404", description = "Bicycle not found"),
    })
    public ResponseEntity<String> generateQRCode(@Parameter(description = "ID of the bicycle") @PathVariable Long id)
            throws WriterException {
        try {
            Bicycle bicycle = bicycleService.getBicycleById(id);

            String qr = QRCodeGenerator.generateQRCode(BicycleMapper.toDto(bicycle));

            return new ResponseEntity<>(qr, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}