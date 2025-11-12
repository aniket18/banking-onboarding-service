package com.banking.onboarding_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class KycDocumentRequest {

    private String documentType;
    private String documentNumber;
    private String fileUrl;
    private String remarks;
}
