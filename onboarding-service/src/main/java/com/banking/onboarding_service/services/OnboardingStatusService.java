package com.banking.onboarding_service.services;

import com.banking.onboarding_service.model.OnboardingStatus;

public interface OnboardingStatusService {

    OnboardingStatus getStatus(Long customerId);
    OnboardingStatus updateStage(Long customerId, String newStage);
}
