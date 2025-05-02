package com.wheel.safe.wheelsafe.bicycle.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Bicycle entity in the system.
 * This entity is used to store and manage information about bicycles,
 * including their physical attributes and specifications.
 * 
 * Attributes:
 * - id: Unique identifier for the bicycle.
 * - model: The model name of the bicycle.
 * - brand: The brand or manufacturer of the bicycle.
 * - serialNumber: A unique serial number for identifying the bicycle.
 * - shopId: (Transient) The ID of the shop where the bicycle is registered. 
 *           This should be moved to the Shop entity.
 * - ownerId: (Transient) The ID of the current owner (user). 
 *            This should be moved to the Shop entity.
 * - status: (Transient) The current status of the bicycle (e.g., available, sold, in repair). 
 *           This should be moved to the Shop entity.
 * - color: The color of the bicycle.
 * - type: The type of bicycle (e.g., mountain, road, hybrid).
 * - size: The size of the bicycle (e.g., small, medium, large).
 * - frameMaterial: The material of the bicycle frame (e.g., aluminum, carbon, steel).
 * - gearSystem: The type of gear system (e.g., manual, automatic).
 * - brakeType: The type of brakes (e.g., disc, rim).
 * - tireSize: The size of the tires (e.g., 26", 29").
 * - accessories: A list of accessories included with the bicycle (e.g., lights, bell, lock).
 * 
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "bicycles")

public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private String brand;
    private String serialNumber;
    @Transient
    private Long shopId; // ID of the shop where the bicycle is registered. Move to Shop entity
    @Transient
    private Long ownerId; // ID of the current owner (user). Move to shop entity
    @Transient
    private String status; // Status of the bicycle (e.g., available, sold, in repair). Move to Shop entity
    private String color;
    private String type; // Type of bicycle (e.g., mountain, road, hybrid). 
    private String size; // Size of the bicycle (e.g., small, medium, large). 
    private String frameMaterial; // Material of the bicycle frame (e.g., aluminum, carbon, steel).
    private String gearSystem; // Type of gear system (e.g., manual, automatic). 
    private String brakeType; // Type of brakes (e.g., disc, rim). 
    private String tireSize; // Size of the tires (e.g., 26", 29").
    private String accessories; // List of accessories (e.g., lights, bell, lock).
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
