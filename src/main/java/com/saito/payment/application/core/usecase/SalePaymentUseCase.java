package com.saito.payment.application.core.usecase;

import com.saito.payment.application.core.domain.Payment;
import com.saito.payment.application.core.domain.Sale;
import com.saito.payment.application.core.domain.enums.SaleEvent;
import com.saito.payment.application.ports.in.FindUserByIdInputPort;
import com.saito.payment.application.ports.out.SavePaymentOutputPort;
import com.saito.payment.application.ports.out.SendValidatedPaymentOutputPort;
import com.saito.payment.application.ports.out.UpdateUserOutputPort;

public class SalePaymentUseCase {
    private final FindUserByIdInputPort findUserByIdInputPort;
    private final UpdateUserOutputPort updateUserOutputPort;
    private final SavePaymentOutputPort savePaymentOutputPort;
    private final SendValidatedPaymentOutputPort sendValidatedPaymentOutputPort;

    public SalePaymentUseCase(FindUserByIdInputPort findUserByIdInputPort,
                              UpdateUserOutputPort updateUserOutputPort,
                              SavePaymentOutputPort savePaymentOutputPort,
                              SendValidatedPaymentOutputPort sendValidatedPaymentOutputPort
    ){
        this.findUserByIdInputPort = findUserByIdInputPort;
        this.updateUserOutputPort = updateUserOutputPort;
        this.savePaymentOutputPort = savePaymentOutputPort;
        this.sendValidatedPaymentOutputPort = sendValidatedPaymentOutputPort;
    }

    public void payment(Sale aSale){
        var user = findUserByIdInputPort.find(aSale.getUserId());
        if(user.getBalance().compareTo(aSale.getValue()) < 0){
            throw new RuntimeException("Insufficient balance.");
        }
        user.debitBalance(aSale.getValue());
        updateUserOutputPort.update(user);
        savePaymentOutputPort.save(buildPayment(aSale));
        sendValidatedPaymentOutputPort.send(aSale, SaleEvent.VALIDATED_PAYMENT);
    }

    private Payment buildPayment(Sale aSale){
        return new Payment(null, aSale.getUserId(), aSale.getId(), aSale.getValue());
    }
}
