package com.example.paymentservice.kafka.dto;

import com.example.paymentservice.dto.InitiatePaymentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private String message;
    private String status;
    private String email;
    private String phoneNumber;
    private Long orderId;
    private Double amount;
}
