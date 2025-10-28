package com.banking.onboarding_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRegistrationRequest {

    @NotBlank(message = "First name is required")
    @Size(max=50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max=50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must be a valid 10-digit number starting with 6-9")
    private String phoneNumber;

    @NotNull(message = "Date Of Birth is required")
    private LocalDate dateOfBirth;

    @NotNull(message = "Address Line 1 is required")
    private String addressLine1;

    @NotNull(message = "Address Line 2 is required")
    private String addressLine2;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "postalCode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "Invalid postalCode format")
    private String postalCode;

    @NotNull(message = "Country is required")
    private String country;
}
