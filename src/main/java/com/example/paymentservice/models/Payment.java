package com.example.paymentservice.models;

import com.example.paymentservice.PaymentServiceApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentReferenceId;
    private String paymentLinkId;
    private String email;
    private String phoneNumber;
    private String orderId;
    private Long amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
