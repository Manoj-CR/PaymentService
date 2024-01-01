package com.example.paymentservice.controller;


import com.example.paymentservice.service.StripeWebhookService;
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
public class StripeWebhookController {



    private StripeWebhookService stripeWebhookService;

    public StripeWebhookController(StripeWebhookService stripeWebhookService){
        this.stripeWebhookService=stripeWebhookService;
    }
    @PostMapping("")
    public void receiveWebhookEvents(@RequestBody String json, HttpServletRequest httpRequest) {
        stripeWebhookService.receiveWebhookEvents(json,httpRequest);
    }

}
