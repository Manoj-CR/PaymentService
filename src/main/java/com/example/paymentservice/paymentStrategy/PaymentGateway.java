package com.example.paymentservice.paymentStrategy;

public interface PaymentGateway {
    public String generatePaymentLink(String orderId,String email, String phoneNumber,Long amount);

}
