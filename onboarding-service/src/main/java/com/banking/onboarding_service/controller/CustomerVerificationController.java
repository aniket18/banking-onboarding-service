package com.banking.onboarding_service.controller;

import com.banking.onboarding_service.dto.CustomerVerificationRequest;
import com.banking.onboarding_service.model.Customer;
import com.banking.onboarding_service.model.CustomerVerification;
import com.banking.onboarding_service.services.CustomerVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerVerificationController {

    @Autowired
    private CustomerVerificationService customerVerificationService;

    @PostMapping("/{customerId}/verify")
    public ResponseEntity<CustomerVerification> verifyCustomer(@PathVariable Long customerId,
                                                               @RequestBody CustomerVerificationRequest request) {
        CustomerVerification verification = customerVerificationService.verifyCustomer(customerId, request);
        return ResponseEntity.ok(verification);
    }

    @PostMapping("/{customerId}/activate")
    public ResponseEntity<Customer> activateCustomer(@PathVariable Long customerId){
        Customer customer = customerVerificationService.activateCustomer(customerId);

        return ResponseEntity.ok(customer);
    }
}
