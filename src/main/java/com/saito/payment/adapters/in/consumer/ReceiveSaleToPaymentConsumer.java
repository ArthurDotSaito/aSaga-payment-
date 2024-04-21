package com.saito.payment.adapters.in.consumer;

import com.saito.payment.adapters.out.message.SaleMessage;
import com.saito.payment.application.core.domain.enums.SaleEvent;
import com.saito.payment.application.ports.in.SalePaymentInputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReceiveSaleToPaymentConsumer {

    @Autowired
    private SalePaymentInputPort salePaymentInputPort;

    @KafkaListener(topics = "tp-saga-sale", groupId = "payment")
    public void receive(SaleMessage aSaleMessage){
        if(SaleEvent.UPDATED_INVENTORY.equals(aSaleMessage.getSaleEvent())){
            log.info("Starting payment...");

            salePaymentInputPort.payment(aSaleMessage.getSale());

            log.info("Payment finished");
        }
    }
}
