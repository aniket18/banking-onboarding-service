package com.banking.onboarding_service.services.impl;

import com.banking.onboarding_service.model.OnboardingStatus;
import com.banking.onboarding_service.repository.CustomerRepository;
import com.banking.onboarding_service.repository.OnboardingStatusRepository;
import com.banking.onboarding_service.services.OnboardingStatusService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OnboardingStatusServiceImpl implements OnboardingStatusService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OnboardingStatusRepository onboardingStatusRepository;
    @Override
    public OnboardingStatus getStatus(Long customerId) {
       return onboardingStatusRepository.findByCustomerId(customerId)
               .orElseThrow(() -> new RuntimeException("Onboarding status not found for customer id: " + customerId));
    }
    @Override
    public OnboardingStatus updateStage(Long customerId, String newStage) {
        OnboardingStatus status = onboardingStatusRepository.findByCustomerId(customerId)
                .orElseGet( () ->{
                   OnboardingStatus s = new OnboardingStatus();
                   s.setCustomerId(customerId);
                   return s;
                });

        //Update stage flag based on current stage
        switch (newStage){
            case "VERIFIED":
                status.setVerificationCompleted(true);
                break;
            case "KYC_DONE":
                status.setKycCompleted(true);
                break;
            case "ACTIVE":
                status.setActivated(true);
                break;
            default:
                status.setRegistrationCompleted(true);
        }

        status.setCurrentStage(newStage.toUpperCase());
        status.setLastUpdated(LocalDateTime.now());
        return onboardingStatusRepository.save(status);
    }
}
