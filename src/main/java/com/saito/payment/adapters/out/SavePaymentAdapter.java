package com.saito.payment.adapters.out;

import com.saito.payment.adapters.out.mapper.PaymentEntityMapper;
import com.saito.payment.adapters.out.repository.PaymentRepository;
import com.saito.payment.application.core.domain.Payment;
import com.saito.payment.application.ports.out.SavePaymentOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SavePaymentAdapter implements SavePaymentOutputPort {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentEntityMapper paymentEntityMapper;

    @Override
    public void save(Payment payment) {
        var paymentEntity = paymentEntityMapper.toPaymentEntity(payment);
        paymentRepository.save(paymentEntity);
    }
}
