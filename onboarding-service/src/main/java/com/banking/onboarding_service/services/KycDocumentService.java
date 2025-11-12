package com.banking.onboarding_service.services;

import com.banking.onboarding_service.dto.KycDocumentRequest;
import com.banking.onboarding_service.dto.KycVerificationRequest;
import com.banking.onboarding_service.model.KycDocument;

import java.util.List;

public interface KycDocumentService {

    KycDocument uploadDocument(Long customerId, KycDocumentRequest request);

    List<KycDocument> getDocumentsByCustomerId(Long customerId);

    KycDocument verifyDocument(Long documentId,
                               Long customerId,
                               KycVerificationRequest request);

    boolean isKycVerified(Long customerId);
}
