package com.banking.onboarding_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="kyc_document",schema = "onboarding")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KycDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long documentId;

    @Column(name="customer_id",nullable = false)
    private Long customerId;

    @Column(name="document_type",nullable = false, length = 50)
    private String documentType;

    @Column(name="document_number",nullable = false, length = 50)
    private String documentNumber;

    @Column(name="file_url",length = 500)
    private String fileUrl;

    @Column(name="uploaded_At")
    private LocalDateTime uploadedAt = LocalDateTime.now();

    @Column(name="verified")
    private Boolean verified = false;

    @Column(name="verified_at")
    private LocalDateTime verifiedAt;

    @Column(name="remarks")
    private String remarks;
}
