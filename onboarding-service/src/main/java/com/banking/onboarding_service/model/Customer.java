package com.banking.onboarding_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer",schema = "onboarding")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name", nullable = false,length = 100)
    private String firstName;
    @Column(name = "last_name", nullable = false,length = 100)
    private String lastName;
    @Column(name = "email", nullable = false, unique = true,length = 150)
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true,length = 15)
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "address_line1", length = 255)
    private String addressLine1;
    @Column(name = "address_line2", length = 255)
    private String addressLine2;
    @Column(name = "city", length = 100)
    private String city;
    @Column(name="state", length = 100)
    private String state;
    @Column(name="postal_code", length = 20)
    private String postalCode;
    @Column(name="country", length = 100)
    private String country;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name="is_verified", nullable = false)
    private boolean isVerified;
    @Column(name="is_active", nullable = false)
    private boolean isActive;

}
