package com.saito.payment.application.ports.in;

import com.saito.payment.application.core.domain.Sale;

public interface SalePaymentInputPort {

    void payment(Sale aSale);
}
