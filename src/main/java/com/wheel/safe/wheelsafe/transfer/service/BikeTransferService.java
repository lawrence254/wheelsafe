package com.wheel.safe.wheelsafe.transfer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.transfer.dto.BikeTransferRequestDTO;
import com.wheel.safe.wheelsafe.transfer.dto.BikeTransferResponseDTO;
import com.wheel.safe.wheelsafe.transfer.entity.BikeTransfer;
import com.wheel.safe.wheelsafe.transfer.entity.TransferStatus;
import com.wheel.safe.wheelsafe.transfer.exceptions.BikeTransferNotFoundException;
import com.wheel.safe.wheelsafe.transfer.repository.BikeTransferRepository;

import lombok.RequiredArgsConstructor;

/*
 * BikeTransferService.java
 * @Author: Lawrence Karanja
 * @GitHub: @lawrence254
 * @Date: 2023-10-01
 * @Description: Service class for managing bike transfers.
 * 
 */

@Service
@RequiredArgsConstructor
public class BikeTransferService {
    private final BikeTransferRepository bikeTransferRepository;

    public BikeTransferResponseDTO createTransfer(BikeTransferRequestDTO request) {
        BikeTransfer bikeTransfer = request.toEntity();
        BikeTransfer savedTransfer = bikeTransferRepository.save(bikeTransfer);
        
        return BikeTransferResponseDTO.fromEntity(savedTransfer);
    }

    public BikeTransferResponseDTO getTransferById(Long transferId) {
        BikeTransfer bikeTransfer = bikeTransferRepository.findById(transferId)
                .orElseThrow(() -> new BikeTransferNotFoundException(transferId));
        
        return BikeTransferResponseDTO.fromEntity(bikeTransfer);
    }
    public BikeTransferResponseDTO updateTransferStatus(Long transferId, String status) {
        BikeTransfer bikeTransfer = bikeTransferRepository.findById(transferId)
                .orElseThrow(() -> new BikeTransferNotFoundException(transferId));
        
        bikeTransfer.setTransferStatus(TransferStatus.valueOf(status.toUpperCase()));
        BikeTransfer updatedTransfer = bikeTransferRepository.save(bikeTransfer);
        
        return BikeTransferResponseDTO.fromEntity(updatedTransfer);
    }

    public List<BikeTransferResponseDTO> getAllTransfers() {
        return bikeTransferRepository.findAll().stream()
                .map(BikeTransferResponseDTO::summaryFromEntity)
                .toList();
    }

    public List<BikeTransferResponseDTO> getTransfersByStatus(String status) {
        return bikeTransferRepository.findByTransferStatus(status).stream()
                .map(BikeTransferResponseDTO::summaryFromEntity)
                .toList();
    }
    public List<BikeTransferResponseDTO> getTransfersByBicycleId(Long bicycleId) {
        return bikeTransferRepository.findByBicycleId(bicycleId).stream()
                .map(BikeTransferResponseDTO::summaryFromEntity)
                .toList();
    }
    public List<BikeTransferResponseDTO> getTransfersByPreviousOwnerId(Long previousOwnerId) {
        return bikeTransferRepository.findByPreviousOwnerId(previousOwnerId).stream()
                .map(BikeTransferResponseDTO::summaryFromEntity)
                .toList();
    }
    public List<BikeTransferResponseDTO> getTransfersByNewOwnerId(Long newOwnerId) {
        return bikeTransferRepository.findByNewOwnerId(newOwnerId).stream()
                .map(BikeTransferResponseDTO::summaryFromEntity)
                .toList();
    }
    public void deleteTransfer(Long transferId) {
        BikeTransfer bikeTransfer = bikeTransferRepository.findById(transferId)
                .orElseThrow(() -> new BikeTransferNotFoundException(transferId));
        
        bikeTransferRepository.delete(bikeTransfer);
    }

}
