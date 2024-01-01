package com.example.paymentservice.kafka.dto;

import jakarta.persistence.Column;

public class Order {

    private String email;
    private String phoneNumber;
    private String orderId;
    private Long amount;

}
