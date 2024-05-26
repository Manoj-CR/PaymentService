package com.example.paymentservice.paymentStrategy;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RazorPayPaymentStrategy implements PaymentGateway {

    private RazorpayClient razorpay;

    public RazorPayPaymentStrategy(RazorpayClient razorpay) {
        this.razorpay = razorpay;
    }


    @Override
    public String generatePaymentLink(String orderId, String email, String phoneNumber, Double amount) {
        try {
            //RazorpayClient razorpay = new RazorpayClient("[YOUR_KEY_ID]", "[YOUR_KEY_SECRET]");
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",false);
            //  paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",1704067199);
            paymentLinkRequest.put("reference_id",orderId);
            paymentLinkRequest.put("description","Payment for Order Id: "+ orderId);
            JSONObject customer = new JSONObject();
            customer.put("name",phoneNumber);
            customer.put("contact","Manoj C R");
            customer.put("email",email);
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            //JSONObject notes = new JSONObject();
            // notes.put("policy_name","Jeevan Bima");
            //paymentLinkRequest.put("notes",notes);
            paymentLinkRequest.put("callback_url","https://example-callback-url.com/");
            paymentLinkRequest.put("callback_method","get");


            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url").toString();
        } catch (RazorpayException e) {
            throw new RuntimeException(e + "Something is wrong");
        }

    }

}
