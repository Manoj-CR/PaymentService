package com.example.paymentservice.controller;

import com.example.paymentservice.dto.InitiatePaymentDto;
import com.example.paymentservice.models.Payment;
import com.example.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    //Logger logger=Logger.getLogger(PaymentController.class);
 Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/")
    public ResponseEntity<String> initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto){
        logger.info("Payment Request DTO {}",initiatePaymentDto);

        try {
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add("Content-Type", "application/json");
            header.add("Accept", "application/json");
            String url=paymentService.initiatePayment(initiatePaymentDto.getAmount(),initiatePaymentDto.getEmail(),
                    initiatePaymentDto.getOrderId(),initiatePaymentDto.getPhoneNumber());
            return new ResponseEntity<>(url,header, HttpStatus.OK);
        }catch (Exception e){
            ResponseEntity<String> responseEntity = new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error code
        }
    }

}
