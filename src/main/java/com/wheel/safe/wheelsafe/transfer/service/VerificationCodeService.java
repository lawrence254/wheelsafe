package com.wheel.safe.wheelsafe.transfer.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.transfer.dto.VerificationResult;
import com.wheel.safe.wheelsafe.transfer.entity.CodeType;
import com.wheel.safe.wheelsafe.transfer.entity.VerificationCode;
import com.wheel.safe.wheelsafe.transfer.repository.VerificationCodeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final SecureRandom secureRandom;

    
    private static final int CODE_LENGTH = 32; // 256-bit security
    private static final int DEFAULT_EXPIRY_HOURS = 24;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public VerificationCodeService(VerificationCodeRepository verificationCodeRepository) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.secureRandom = new SecureRandom();
    }

    public String generateTransferCode(Long bicycleId, Long createdBy, int expiryHours) {
        // Deactivate any existing active codes for this bicycle
        verificationCodeRepository.deactivateCodesByEntityAndType(bicycleId, CodeType.BIKE_TRANSFER);

        String code = generateSecureCode();

        VerificationCode verificationCode = VerificationCode.builder()
                .code(code)
                .codeType(CodeType.BIKE_TRANSFER)
                .entityId(bicycleId)
                .createdBy(createdBy)
                .expiresAt(LocalDateTime.now().plusHours(expiryHours))
                .maxUsage(1) // Single use for security
                .build();

        verificationCodeRepository.save(verificationCode);

        log.info("Generated transfer verification code for bicycle ID: {}, expires at: {}",
                bicycleId, verificationCode.getExpiresAt());

        return code;
    }

    /**
     * Generate with default expiry
     */
    public String generateTransferCode(Long bicycleId, Long createdBy) {
        return generateTransferCode(bicycleId, createdBy, DEFAULT_EXPIRY_HOURS);
    }

    
    public VerificationResult validateTransferCode(String code, Long bicycleId) {
        if (code == null || code.trim().isEmpty()) {
            return VerificationResult.invalid("Verification code is required");
        }

        
        cleanupExpiredCodes();

        Optional<VerificationCode> optionalCode = verificationCodeRepository
                .findByCodeAndCodeTypeAndIsActiveTrue(code.trim().toUpperCase(), CodeType.BIKE_TRANSFER);

        if (optionalCode.isEmpty()) {
            log.warn("Invalid or expired verification code attempted: {}", code);
            return VerificationResult.invalid("Invalid or expired verification code");
        }

        VerificationCode verificationCode = optionalCode.get();

        
        if (!verificationCode.getEntityId().equals(bicycleId)) {
            log.warn("Verification code {} does not match bicycle ID: {}", code, bicycleId);
            return VerificationResult.invalid("Verification code does not match this bicycle");
        }

        
        if (!verificationCode.isUsable()) {
            log.warn("Verification code {} is no longer usable", code);
            return VerificationResult.invalid("Verification code has been used or expired");
        }

        
        verificationCode.markAsUsed();
        verificationCodeRepository.save(verificationCode);

        log.info("Successfully validated and consumed verification code for bicycle ID: {}", bicycleId);

        return VerificationResult.valid(verificationCode);
    }

    private String generateSecureCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }

        // Ensure uniqueness
        String generatedCode = code.toString();
        while (verificationCodeRepository.existsByCodeAndIsActiveTrue(generatedCode)) {
           
            code = new StringBuilder(CODE_LENGTH);
            for (int i = 0; i < CODE_LENGTH; i++) {
                int randomIndex = secureRandom.nextInt(CHARACTERS.length());
                code.append(CHARACTERS.charAt(randomIndex));
            }
            generatedCode = code.toString();
        }

        return generatedCode;
    }

    @Scheduled(fixedRate = 3600000) // Run every hour
    public void cleanupExpiredCodes() {
        int deactivated = verificationCodeRepository.deactivateExpiredCodes(LocalDateTime.now());
        if (deactivated > 0) {
            log.info("Deactivated {} expired verification codes", deactivated);
        }
    }

    
    public List<VerificationCode> getActiveCodesForBicycle(Long bicycleId) {
        return verificationCodeRepository.findByEntityIdAndCodeTypeAndIsActiveTrue(bicycleId, CodeType.BIKE_TRANSFER);
    }

    
    public void revokeCodesForBicycle(Long bicycleId) {
        verificationCodeRepository.deactivateCodesByEntityAndType(bicycleId, CodeType.BIKE_TRANSFER);
        log.info("Revoked all active verification codes for bicycle ID: {}", bicycleId);
    }
}
