package com.banking.onboarding_service.services;

import com.banking.onboarding_service.dto.*;


public interface CustomerService {

    CustomerRegistrationResponse registerCustomer(CustomerRegistrationRequest request);

    CustomerProfileResponse getCustomerProfile(Long customerId);

    CustomerUpdateResponse updateCustomer(Long customerId, CustomerUpdateRequest request);


}
