package com.saito.payment.adapters.in.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveSaleToPaymentConsumer {

    @KafkaListener(topics = "tp-saga-sale", groupId = "payment")
    public void receive(){

    }
}
