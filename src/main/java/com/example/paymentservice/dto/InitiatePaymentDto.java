package com.example.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentDto {

   private String email;
   private String phoneNumber;
   private Long orderId;
   private Double amount;
}
