package com.example.paymentservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Metadata {

    private String email;
    private String phoneNumber;
    private String orderId;
    private String amount;
}
