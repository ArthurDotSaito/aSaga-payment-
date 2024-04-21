package com.saito.payment.application.ports.out;

import com.saito.payment.application.core.domain.Sale;
import com.saito.payment.application.core.domain.enums.SaleEvent;

public interface SendToKafkaOutputPort {

    void send(Sale aSale, SaleEvent event);
}
