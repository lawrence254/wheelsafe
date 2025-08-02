package com.wheel.safe.wheelsafe.transfer.dto;

import com.wheel.safe.wheelsafe.transfer.entity.VerificationCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationResult {
    private boolean valid;
    private String message;
    private VerificationCode verificationCode;

    public static VerificationResult valid(VerificationCode code) {
        return new VerificationResult(true, "Code verified successfully", code);
    }

    public static VerificationResult invalid(String message) {
        return new VerificationResult(false, message, null);
    }
}