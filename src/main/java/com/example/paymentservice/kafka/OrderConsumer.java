//package com.example.paymentservice.kafka;
//
//import com.example.paymentservice.kafka.dto.OrderEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
//
//    @KafkaListener(topics = "${spring.kafka.topic.order.name}", groupId = "${spring.kafka.consumer.group-id}")
//    public void consumeOrderEvent(OrderEvent orderEvent){
//        logger.info(String.format("Order Event received in Payment Service=> %s", orderEvent.toString()));
//
//
//    }
//
//
//}
