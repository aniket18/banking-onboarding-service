package com.banking.onboarding_service.controller;

import com.banking.onboarding_service.model.OnboardingStatus;
import com.banking.onboarding_service.services.OnboardingStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class OnboardingStatusController {

    @Autowired
    private OnboardingStatusService onboardingStatusService;

    @GetMapping("/{customerId}/status")
    public ResponseEntity<OnboardingStatus> getStatus(@PathVariable Long customerId){
         OnboardingStatus status = onboardingStatusService.getStatus(customerId);
         return ResponseEntity.ok(status);
    }

    @PutMapping("/{customerId}/updateStage")
    public ResponseEntity<OnboardingStatus> updateStage(@PathVariable Long customerId,
                                                        @RequestParam String newStage){

       OnboardingStatus status = onboardingStatusService.updateStage(customerId, newStage);
       return ResponseEntity.ok(status);
    }
}
