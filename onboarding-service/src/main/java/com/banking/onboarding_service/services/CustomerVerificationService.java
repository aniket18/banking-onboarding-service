package com.banking.onboarding_service.services;

import com.banking.onboarding_service.dto.CustomerVerificationRequest;
import com.banking.onboarding_service.model.Customer;
import com.banking.onboarding_service.model.CustomerVerification;

public interface CustomerVerificationService {

    CustomerVerification verifyCustomer(long customerId,
                                        CustomerVerificationRequest request);

    Customer activateCustomer(long customerId);
}
