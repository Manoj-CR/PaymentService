package com.example.paymentservice.paymentStrategy;

import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class PaymentGatewayChooserStrategy {

    private RazorPayPaymentStrategy razorPayPaymentStrategy;
    private StripePaymentStrategy stripePaymentStrategy;

    public PaymentGatewayChooserStrategy(RazorPayPaymentStrategy razorPayPaymentStrategy,
                                         StripePaymentStrategy stripePaymentStrategy) {
        this.razorPayPaymentStrategy = razorPayPaymentStrategy;
        this.stripePaymentStrategy = stripePaymentStrategy;
    }

    public PaymentGateway getBestPaymentGateway(){
//        int randomInt = new Random().nextInt();
//
//        if(randomInt%2==0){
//            return  stripePaymentStrategy;
//        }
        return stripePaymentStrategy;
    }
}
