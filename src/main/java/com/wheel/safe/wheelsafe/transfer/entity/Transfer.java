package com.wheel.safe.wheelsafe.transfer.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.wheel.safe.wheelsafe.bicycle.entity.Bicycle;
import com.wheel.safe.wheelsafe.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@Table(name = "transfer_records")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"verificationCode"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id", nullable = false)
    private Bicycle bicycle;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_owner_id", nullable = false)
    private User previousOwner;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "new_owner_id", nullable = false)
    private User newOwner;

    @Column(name = "transfer_date", nullable = false)
    @Builder.Default
    private LocalDate transferDate = LocalDate.now();

    @Column(name="transfer_reason")
    private String transferReason;

    @Column(name="cost")
    private Double cost;
    @Column(name = "verification_code", length = 100)
    private String verificationCode;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}   

