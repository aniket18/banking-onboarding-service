package com.banking.onboarding_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="customer_verification",schema = "onboarding")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CustomerVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_id")
    private long verificationId;

    @Column(name="customer_id",nullable = false)
    private long customerId;

    @Column(name="email_verified",nullable = false)
    private boolean emailVerified = false;

    @Column(name="phone_verified",nullable = false)
    private boolean phoneVerified = false;

    @Column(name="kyc_verified",nullable = false)
    private boolean kycVerified = false;

    @Column(name="verification_notes")
    private String verificationNotes;

    @Column(name="verified_at")
    private LocalDateTime verifiedAt;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
