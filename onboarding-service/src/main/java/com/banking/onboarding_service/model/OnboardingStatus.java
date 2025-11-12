package com.banking.onboarding_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="onboarding_status",schema = "onboarding")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OnboardingStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="status_id")
    private Long statusId;

    @Column(name="customer_id",nullable = false)
    private Long customerId;

    @Column(name="registration_completed")
    private Boolean registrationCompleted = false;

    @Column(name = "verification_completed")
    private Boolean verificationCompleted = false;

    @Column(name = "kyc_completed")
    private Boolean kycCompleted = false;

    @Column(name ="profile_completed")
    private Boolean profileCompleted = false;

    @Column(name="activated")
    private Boolean activated;

    @Column(name="current_stage",length = 50)
    private String currentStage = "REGISTERED";

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

}
