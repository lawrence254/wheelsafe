package com.wheel.safe.wheelsafe.transfer.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.wheel.safe.wheelsafe.transfer.entity.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wheel.safe.wheelsafe.transfer.entity.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    Optional<VerificationCode> findByCodeAndIsActiveTrue(String code);
    
    Optional<VerificationCode> findByCodeAndCodeTypeAndIsActiveTrue(String code, CodeType codeType);
    
    List<VerificationCode> findByEntityIdAndCodeTypeAndIsActiveTrue(Long entityId, CodeType codeType);
    
    List<VerificationCode> findByCreatedByAndCodeType(Long createdBy, CodeType codeType);
    
    @Modifying
    @Query("UPDATE VerificationCode v SET v.isActive = false WHERE v.expiresAt < :now")
    int deactivateExpiredCodes(@Param("now") LocalDateTime now);
    
    @Modifying
    @Query("UPDATE VerificationCode v SET v.isActive = false WHERE v.entityId = :entityId AND v.codeType = :codeType")
    int deactivateCodesByEntityAndType(@Param("entityId") Long entityId, @Param("codeType") CodeType codeType);
    
    boolean existsByCodeAndIsActiveTrue(String code);
}