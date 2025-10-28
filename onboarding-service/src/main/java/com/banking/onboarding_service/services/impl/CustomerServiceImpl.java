package com.banking.onboarding_service.services.impl;

import com.banking.onboarding_service.dto.*;

import com.banking.onboarding_service.model.Customer;
import jakarta.transaction.Transactional;
import lombok.extern.flogger.Flogger;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banking.onboarding_service.repository.CustomerRepository;
import com.banking.onboarding_service.services.CustomerService;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional
    public CustomerRegistrationResponse registerCustomer(
            CustomerRegistrationRequest request) {

        if(customerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        if(customerRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already registered");
        }

        //Map DTO to Entity
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setDateOfBirth(request.getDateOfBirth());
        customer.setAddressLine1(request.getAddressLine1());
        customer.setAddressLine2(request.getAddressLine2());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setPostalCode(request.getPostalCode());
        customer.setCountry(request.getCountry());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setVerified(false);
        customer.setActive(false);

        //save to DB
        Customer saved = customerRepository.save(customer);
        logger.trace("Registered new customer with ID: " + saved.getCustomerId());

        //Map Entity Response to DTO
        return mapToRegistrationResponse(saved);
    }

    @Override
    public CustomerProfileResponse getCustomerProfile(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        logger.trace("Fetched profile for customer ID: " + customerId);
        return mapToProfileResponse(customer);
    }

    @Override
    @Transactional
    public CustomerUpdateResponse updateCustomer(Long customerId, CustomerUpdateRequest request) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        // Update fields
        if(request.getFirstName() != null)
            customer.setFirstName(request.getFirstName());
        if(request.getLastName() != null)
            customer.setLastName(request.getLastName());
        if(request.getEmail() != null)
            customer.setEmail(request.getEmail());
        if(request.getPhoneNumber() != null)
            customer.setPhoneNumber(request.getPhoneNumber());
        if(request.getDateOfBirth() != null)
            customer.setDateOfBirth(request.getDateOfBirth());
        if(request.getAddressLine1() != null)
            customer.setAddressLine1(request.getAddressLine1());
        if (request.getAddressLine2() != null)
            customer.setAddressLine2(request.getAddressLine2());
        if(request.getCity() != null)
            customer.setCity(request.getCity());
        if(request.getState() != null)
            customer.setState(request.getState());
        if(request.getPostalCode() != null)
            customer.setPostalCode(request.getPostalCode());
        if(request.getCountry() != null)
            customer.setCountry(request.getCountry());

        customer.setUpdatedAt(LocalDateTime.now());
        //Save to DB
        Customer updated = customerRepository.save(customer);
        logger.trace("Updated customer with ID: " + updated.getCustomerId());

        return mapToUpdateResponse(updated);
    }

    // DTO Mapping Methods
    private CustomerRegistrationResponse mapToRegistrationResponse(Customer customer){
        CustomerRegistrationResponse response = new CustomerRegistrationResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setDateOfBirth(customer.getDateOfBirth());
        response.setAddressLine1(customer.getAddressLine1());
        response.setAddressLine2(customer.getAddressLine2());
        response.setCity(customer.getCity());
        response.setState(customer.getState());
        response.setPostalCode(customer.getPostalCode());
        response.setCountry(customer.getCountry());
        response.setVerified(customer.isVerified());
        response.setActive(customer.isActive());
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());
        return response;
    }

   private CustomerProfileResponse mapToProfileResponse(Customer customer){
        CustomerProfileResponse response = new CustomerProfileResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setFirstName(customer.getFirstName());
        response.setLastName(customer.getLastName());
        response.setEmail(customer.getEmail());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setDateOfBirth(customer.getDateOfBirth());
        response.setAddressLine1(customer.getAddressLine1());
        response.setAddressLine2(customer.getAddressLine2());
        response.setCity(customer.getCity());
        response.setState(customer.getState());
        response.setPostalCode(customer.getPostalCode());
        response.setCountry(customer.getCountry());
        response.setVerified(customer.isVerified());
        response.setActive(customer.isActive());

        return response;
   }

   private CustomerUpdateResponse mapToUpdateResponse(Customer customer){
        CustomerUpdateResponse response = new CustomerUpdateResponse();

        response.setCustomerId(customer.getCustomerId());
        response.setVerified(customer.isVerified());
        response.setActive(customer.isActive());
        response.setUpdatedAt(customer.getUpdatedAt());

        return response;
   }
}
