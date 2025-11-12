package com.banking.onboarding_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KycVerificationRequest {
    private Boolean verified;
    private String remarks;
}
