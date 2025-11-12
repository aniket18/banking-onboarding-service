package com.banking.onboarding_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CustomerVerificationRequest {

    private Boolean emailVerified;
    private Boolean phoneVerified;
    //private Boolean kycVerified; -- Auto handled based on KYC documents
    private String verificationNotes;

}
