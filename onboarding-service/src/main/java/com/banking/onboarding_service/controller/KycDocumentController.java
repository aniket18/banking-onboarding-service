package com.banking.onboarding_service.controller;

import com.banking.onboarding_service.dto.KycDocumentRequest;
import com.banking.onboarding_service.dto.KycVerificationRequest;
import com.banking.onboarding_service.model.KycDocument;
import com.banking.onboarding_service.services.KycDocumentService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class KycDocumentController {


    @Autowired
    private KycDocumentService kycDocumentService;

    @PostMapping("/{customerId}/uploadDocuments")
    public ResponseEntity<KycDocument> uploadDocument(@PathVariable Long customerId,
                                                      @RequestBody KycDocumentRequest request){

       KycDocument document = kycDocumentService.uploadDocument(customerId,request);
       return ResponseEntity.ok(document);
    }

    @GetMapping("/{customerId}/getDocuments")
    public ResponseEntity<List<KycDocument>> getCustomerDocuments(@PathVariable Long customerId){
        List<KycDocument> documents = kycDocumentService.getDocumentsByCustomerId(customerId);

        return ResponseEntity.ok(documents);
    }

    @PostMapping("/{customerId}/document/{documentId}/verify")
    public ResponseEntity<KycDocument> verifyKycDocument(@PathVariable Long documentId,
                                      @PathVariable Long customerId,
                                      @RequestBody KycVerificationRequest request){

       KycDocument updateDoc = kycDocumentService.verifyDocument(documentId,customerId,request);

       return ResponseEntity.ok(updateDoc);
    }
}
