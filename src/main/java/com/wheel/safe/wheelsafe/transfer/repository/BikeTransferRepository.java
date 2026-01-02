package com.wheel.safe.wheelsafe.transfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wheel.safe.wheelsafe.transfer.entity.BikeTransfer;

public interface BikeTransferRepository extends JpaRepository<BikeTransfer, Long> {
    List<BikeTransfer> findByTransferStatus(String transferStatus);
    List<BikeTransfer> findByBicycleId(Long bicycleId);
    
    List<BikeTransfer> findByPreviousOwnerId(Long previousOwnerId);
    
    List<BikeTransfer> findByNewOwnerId(Long newOwnerId);

    
} 
