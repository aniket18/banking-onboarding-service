package com.banking.onboarding_service.repository;

import com.banking.onboarding_service.model.OnboardingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OnboardingStatusRepository extends JpaRepository<OnboardingStatus,Long> {

    Optional<OnboardingStatus> findByCustomerId(Long customerId);
}
