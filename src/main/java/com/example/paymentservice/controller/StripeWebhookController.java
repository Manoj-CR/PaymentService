package com.example.paymentservice.controller;


import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripeWebhook")
public class StripeWebhook {

@Value("${Stripe.key.webhooksSecret}")
    private String endpointSecret ;
    @PostMapping("")
    public void receiveWebhookEvents(@RequestBody String json, HttpServletRequest httpRequest) {
        System.out.println("Waiting");
        String header = httpRequest.getHeader("Stripe-Signature");

        Event event = validateStripeHeadersAndReturnEvent(json, httpRequest.getHeader("Stripe-Signature"));
System.out.println("Waiting");
        if (event.getType().equals("payment_intent.succeeded")) {
            System.out.println("Payment Sucess");
        }
    }

    private Event validateStripeHeadersAndReturnEvent(String payload, String headers) {
        try {
            return Webhook.constructEvent(
                    payload, headers, endpointSecret
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid payload"+ e.getMessage());
        }
    }
}
