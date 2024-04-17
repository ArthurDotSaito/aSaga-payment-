package com.saito.payment.adapters.out;

import com.saito.payment.adapters.out.message.SaleMessage;
import com.saito.payment.application.core.domain.Sale;
import com.saito.payment.application.core.domain.enums.SaleEvent;
import com.saito.payment.application.ports.out.SendValidatedPaymentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendValidatedPaymentAdapter implements SendValidatedPaymentOutputPort {

    @Autowired
    private KafkaTemplate<String, SaleMessage> kafkaTemplate;

    @Override
    public void send(Sale aSale, SaleEvent event) {
        var saleMessage = new SaleMessage(aSale, event);
        kafkaTemplate.send("tp-saga-sale", saleMessage);
    }
}
