package com.saito.payment.adapters.out.mapper;

import com.saito.payment.adapters.out.repository.entity.PaymentEntity;
import com.saito.payment.application.core.domain.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    PaymentEntity toPaymentEntity(Payment aPayment);
}
