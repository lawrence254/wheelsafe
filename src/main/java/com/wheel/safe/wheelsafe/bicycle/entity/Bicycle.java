package com.wheel.safe.wheelsafe.bicycle.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
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
    
}
