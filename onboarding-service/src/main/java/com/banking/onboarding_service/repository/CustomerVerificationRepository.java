package com.banking.onboarding_service.repository;

import com.banking.onboarding_service.model.CustomerVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerVerificationRepository extends JpaRepository<CustomerVerification,Long> {

     Optional<CustomerVerification> findByCustomerId(long customerId);
}
