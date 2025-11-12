package com.banking.onboarding_service.repository;

import com.banking.onboarding_service.model.KycDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KycDocumentRepository extends JpaRepository<KycDocument,Long> {

    List<KycDocument> findByCustomerId(Long customerId);
}
