package com.banking.onboarding_service.services.impl;

import com.banking.onboarding_service.dto.CustomerVerificationRequest;
import com.banking.onboarding_service.model.Customer;
import com.banking.onboarding_service.model.CustomerVerification;
import com.banking.onboarding_service.model.KycDocument;
import com.banking.onboarding_service.repository.CustomerRepository;
import com.banking.onboarding_service.repository.CustomerVerificationRepository;
import com.banking.onboarding_service.services.CustomerVerificationService;
import com.banking.onboarding_service.services.KycDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerVerificationServiceImpl implements CustomerVerificationService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerVerificationRepository customerVerificationRepository;
    @Autowired
    private KycDocumentService kycDocumentService;

    @Override
    public CustomerVerification verifyCustomer(long customerId,
                                               CustomerVerificationRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));

        //Fetch or create new verification record
        CustomerVerification verification =
                customerVerificationRepository.findByCustomerId(customerId)
                        .orElseGet( () -> {
                           CustomerVerification v = new CustomerVerification();
                            v.setCustomerId(customerId);
                            return v;
                        });

        //Update verification details
        if(request.getEmailVerified() != null) {
            verification.setEmailVerified(request.getEmailVerified());
        }
        if(request.getPhoneVerified() != null){
            verification.setPhoneVerified(request.getPhoneVerified());
        }

        /*verification.setVerificationNotes(request.getVerificationNotes());
        verification.setVerifiedAt(LocalDateTime.now());
        customerVerificationRepository.save(verification);*/

        //Update customer verification flag if all verifications are done
        if(verification.isEmailVerified() &&
           verification.isPhoneVerified() &&
           kycDocumentService.isKycVerified(customerId)){
            verification.setVerificationNotes(request.getVerificationNotes());
            verification.setVerifiedAt(LocalDateTime.now());
            customerVerificationRepository.save(verification);
            customer.setVerified(true);
            customer.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(customer);
        }else{
            verification.setVerificationNotes(request.getVerificationNotes());
            verification.setVerifiedAt(LocalDateTime.now());
            customerVerificationRepository.save(verification);
            customer.setVerified(false);
            customer.setUpdatedAt(LocalDateTime.now());
            customerRepository.save(customer);
        }
        return verification;
    }

    @Override
    public Customer activateCustomer(long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(("Customer not " +
                        "found with id:" + customerId)));

        if(!customer.isVerified()){
            throw new IllegalStateException("Customer must be verified before activation");
        }

        customer.setActive(true);
        customer.setUpdatedAt(LocalDateTime.now());
        customerRepository.save(customer);

        return customer;
    }
}
