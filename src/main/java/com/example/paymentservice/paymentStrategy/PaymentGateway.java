package com.example.paymentservice.paymentStrategy;

public interface PaymentGateway {
    //public String generatePaymentLink(Long orderId,String email, String phoneNumber,Double amount);

    public String generatePaymentLink(String orderId, String email, String phoneNumber, Double amount);
}
