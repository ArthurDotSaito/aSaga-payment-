package com.saito.payment.application.core.usecase;

import com.saito.payment.application.core.domain.Sale;
import com.saito.payment.application.ports.in.FindUserByIdInputPort;

public class SalePaymentUseCase {
    private final FindUserByIdInputPort findUserByIdInputPort;

    public SalePaymentUseCase(FindUserByIdInputPort findUserByIdInputPort){
        this.findUserByIdInputPort = findUserByIdInputPort;
    }

    public void payment(Sale aSale){
        var user = findUserByIdInputPort.find(aSale.getUserId());
        if(user.getBalance().compareTo(aSale.getValue()) < 0){
            throw new RuntimeException("Insufficient balance.");
        }
        user.debitBalance(aSale.getValue());
    }
}
