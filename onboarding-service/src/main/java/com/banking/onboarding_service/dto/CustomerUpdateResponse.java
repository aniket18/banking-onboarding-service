package com.banking.onboarding_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateResponse {

    private Long customerId;

    private boolean isVerified;
    private boolean isActive;

    private LocalDateTime updatedAt;
}
