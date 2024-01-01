package com.example.paymentservice.service;

import com.example.paymentservice.models.Payment;
import com.example.paymentservice.models.PaymentStatus;
import com.example.paymentservice.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.LinkedTreeMap;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class StripeWebhookService {

    private PaymentRepository paymentRepository;
 public StripeWebhookService(PaymentRepository paymentRepository){
     this.paymentRepository=paymentRepository;
   }

    @Value("${Stripe.key.webhooksSecret}")
    private String endpointSecret ;
    public void receiveWebhookEvents(String json, HttpServletRequest httpRequest){
        System.out.println("Waiting");
        String header = httpRequest.getHeader("Stripe-Signature");
        Event event = null;
        //validateStripeHeadersAndReturnEvent(json, httpRequest.getHeader("Stripe-Signature"));
        try {
            event= Webhook.constructEvent(
                    json, header, endpointSecret
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid payload"+ e.getMessage());
        }
        System.out.println("Waiting");
        // Handle the event

        switch (event.getType()) {
            case "payment_intent.canceled": {
                System.out.println("Cancelled");
                break;
            }
            case "checkout.session.completed": {
                System.out.println("checkout.session.completed");
                ObjectMapper m = new ObjectMapper();
                @SuppressWarnings("unchecked")
                Map<String, Object> props = m.convertValue(event.getData().getObject(), Map.class);
                Map<String, Object> meta = m.convertValue(props.get("metadata"), Map.class);
                Payment payment =null;
                if(event.getType().equals("checkout.session.completed")) {
                    Optional<Payment> paymentOptional =paymentRepository.findPaymentByPaymentReferenceId((String) props.get("paymentIntent"));
                    if(paymentOptional.isEmpty()){
                        payment= new Payment();
                    }
                    payment.setEmail((String) meta.get("email"));
                    payment.setPhoneNumber((String) meta.get("phoneNumber"));
                    payment.setPaymentLinkId((String) props.get("paymentLink"));
                    payment.setOrderId((String) meta.get("orderId"));
                    payment.setAmount(Long.valueOf((String) meta.get("amount")));
                    payment.setPaymentStatus(PaymentStatus.LINK_CREATED);
                    payment.setPaymentReferenceId((String) props.get("paymentIntent"));
                }
                try {
                    paymentRepository.save(payment);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "payment_intent.created": {
                System.out.println("Created");
                break;
            }
            case "payment_intent.payment_failed": {
                System.out.println("Failure");
                break;
            }
            case "payment_intent.processing": {
                System.out.println("Processing");
                break;
            }
            case "payment_intent.requires_action": {
                System.out.println("Action Required");
                break;
            }
            case "payment_intent.succeeded": {
                System.out.println("Success");
                ObjectMapper m = new ObjectMapper();
                @SuppressWarnings("unchecked")
                Map<String, Object> props = m.convertValue(event.getData().getObject(), Map.class);
                //Map<String, Object> meta = m.convertValue(props.get("metadata"), Map.class);
                String paymRefId=(String) props.get("id");
                Optional<Payment> paymentOptional=paymentRepository.findPaymentByPaymentReferenceId(paymRefId);
               Payment payment=paymentOptional.get();
                // Then define and call a function to handle the event payment_intent.succeeded
                payment.setPaymentStatus(PaymentStatus.SUCCESS);
                try {
                    paymentRepository.save(payment);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "payment_link.created": {
                // Then define and call a function to handle the event payment_link.created

                // Converting event object to map



                break;
            }
        }
    }
}
