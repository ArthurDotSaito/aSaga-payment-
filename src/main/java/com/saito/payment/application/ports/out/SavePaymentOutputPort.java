package com.saito.payment.application.ports.out;

import com.saito.payment.application.core.domain.Payment;

public interface SavePaymentOutputPort {

    void save(Payment payment);
}
