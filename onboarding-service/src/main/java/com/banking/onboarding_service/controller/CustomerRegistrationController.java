package com.banking.onboarding_service.controller;

import com.banking.onboarding_service.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banking.onboarding_service.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerRegistrationController {

    private final CustomerService customerService;

    // Implement customer registration endpoints here
    @PostMapping("/register")
    public ResponseEntity<CustomerRegistrationResponse> registerCustomer(
            @Valid @RequestBody CustomerRegistrationRequest request) {
        // Placeholder implementation
        return ResponseEntity.ok(customerService.registerCustomer(request));
    }

    //Get customer profile by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerProfileResponse> getCustomerProfile(
            @PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerProfile(customerId));
    }

    // Update customer information
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerUpdateResponse> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerUpdateRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(customerId, request));

    }



}
