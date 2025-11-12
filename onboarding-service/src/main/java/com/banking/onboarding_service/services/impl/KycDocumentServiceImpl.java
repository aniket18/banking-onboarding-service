package com.banking.onboarding_service.services.impl;

import com.banking.onboarding_service.dto.CustomerVerificationRequest;
import com.banking.onboarding_service.dto.KycDocumentRequest;
import com.banking.onboarding_service.dto.KycVerificationRequest;
import com.banking.onboarding_service.model.Customer;
import com.banking.onboarding_service.model.CustomerVerification;
import com.banking.onboarding_service.model.KycDocument;
import com.banking.onboarding_service.repository.CustomerRepository;
import com.banking.onboarding_service.repository.CustomerVerificationRepository;
import com.banking.onboarding_service.repository.KycDocumentRepository;
import com.banking.onboarding_service.services.KycDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KycDocumentServiceImpl implements KycDocumentService {

    @Autowired
    private KycDocumentRepository kycDocumentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerVerificationRepository customerVerificationRepository;

    @Override
    public KycDocument uploadDocument(Long customerId, KycDocumentRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: "+ customerId));

        KycDocument kycDocument = new KycDocument();
        kycDocument.setCustomerId(customerId);
        kycDocument.setDocumentType(request.getDocumentType());
        kycDocument.setDocumentNumber(request.getDocumentNumber());
        kycDocument.setFileUrl(request.getFileUrl());
        kycDocument.setRemarks(request.getRemarks());
        kycDocument.setUploadedAt(LocalDateTime.now());

        return kycDocumentRepository.save(kycDocument);
    }

    @Override
    public List<KycDocument> getDocumentsByCustomerId(Long customerId) {

       return kycDocumentRepository.findByCustomerId(customerId);
    }

    @Override
    public KycDocument verifyDocument(Long documentId,
                                      Long customerId,
                                      KycVerificationRequest request){

        KycDocument document = kycDocumentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("KYC Document not found with id: " + documentId));

        if(!document.getCustomerId().equals(customerId)){
            throw new IllegalArgumentException("Document does not belong to the specified customer");
        }
        document.setVerified(request.getVerified());
        document.setVerifiedAt(LocalDateTime.now());
        document.setRemarks(request.getRemarks());

        kycDocumentRepository.save(document);

        //Check if all documents are verified to update customer KYC status
       boolean allVerified = kycDocumentRepository.findByCustomerId(customerId)
                .stream()
                .allMatch(KycDocument::getVerified);

       //If all documents are verified, update customer verification status
       if(allVerified){
           customerVerificationRepository.findByCustomerId(customerId)
                   .ifPresentOrElse(v -> {
                       v.setKycVerified(true);
                       v.setVerificationNotes("Customer Verification Is Completed! All KYC documents are verified.");
                       v.setVerifiedAt(LocalDateTime.now());
                       customerVerificationRepository.save(v);
                   }, () -> {
                       // In case verification record does not exist, create one
                       CustomerVerification verification = new CustomerVerification();
                          verification.setCustomerId(customerId);
                            verification.setKycVerified(true);
                            verification.setVerifiedAt(LocalDateTime.now());
                            customerVerificationRepository.save(verification);
                   } );
       }
       return document;
    }

    @Override
    public boolean isKycVerified(Long customerId){
        return customerVerificationRepository.findByCustomerId(customerId)
                .map(CustomerVerification::isKycVerified)
                .orElse(false);
    }
}
