package com.example.paymentservice.service;

import com.example.paymentservice.controller.PaymentController;
import com.example.paymentservice.models.Payment;
import com.example.paymentservice.models.PaymentStatus;
import com.example.paymentservice.repository.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StripeCronJobService {

    private PaymentRepository paymentRepository;

    @Value("${Stripe.key.secret}")
    private String apiKey;

    public StripeCronJobService(PaymentRepository paymentRepository){
        this.paymentRepository=paymentRepository;
    }
    public void getPendingTransactionStatus() {
        List<Payment> paymentList=paymentRepository.findAllPayment();
        for(Payment payment:paymentList){
            System.out.println(payment.getPaymentReferenceId());

    try{
        Stripe.apiKey=apiKey;
        PaymentIntent paymentIntent = PaymentIntent.retrieve(payment.getPaymentReferenceId());
      if(paymentIntent.getStatus().equals("succeeded")){
          payment.setPaymentStatus(PaymentStatus.SUCCESS);
      }
      //do for other status also
      //Save in DB
        System.out.println(paymentIntent);
    } catch (Exception e){
        throw new RuntimeException(e.getMessage());
}
        }

    }
}
