package com.example.paymentservice.service;

import com.example.paymentservice.controller.PaymentController;
import com.example.paymentservice.paymentStrategy.PaymentGateway;
import com.example.paymentservice.paymentStrategy.PaymentGatewayChooserStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGatewayChooserStrategy paymentGatewayChooserStrategy;

    public PaymentService(PaymentGatewayChooserStrategy paymentGatewayChooserStrategy) {
        this.paymentGatewayChooserStrategy = paymentGatewayChooserStrategy;
    }

    public String initiatePayment(Double amount, String email, Long orderId, String phoneNumber) {

        PaymentGateway paymentGateway=paymentGatewayChooserStrategy.getBestPaymentGateway();

        return paymentGateway.generatePaymentLink(orderId,email,phoneNumber,amount);
    }
}
