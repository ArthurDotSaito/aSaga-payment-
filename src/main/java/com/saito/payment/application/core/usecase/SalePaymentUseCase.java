package com.saito.payment.application.core.usecase;

import com.saito.payment.application.core.domain.Payment;
import com.saito.payment.application.core.domain.Sale;
import com.saito.payment.application.core.domain.enums.SaleEvent;
import com.saito.payment.application.ports.in.FindUserByIdInputPort;
import com.saito.payment.application.ports.in.SalePaymentInputPort;
import com.saito.payment.application.ports.out.SavePaymentOutputPort;
import com.saito.payment.application.ports.out.SendToKafkaOutputPort;
import com.saito.payment.application.ports.out.UpdateUserOutputPort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SalePaymentUseCase implements SalePaymentInputPort {
    private final FindUserByIdInputPort findUserByIdInputPort;
    private final UpdateUserOutputPort updateUserOutputPort;
    private final SavePaymentOutputPort savePaymentOutputPort;
    private final SendToKafkaOutputPort sendToKafkaOutputPort;

    public SalePaymentUseCase(FindUserByIdInputPort findUserByIdInputPort,
                              UpdateUserOutputPort updateUserOutputPort,
                              SavePaymentOutputPort savePaymentOutputPort,
                              SendToKafkaOutputPort sendValidatedPaymentOutputPort
    ){
        this.findUserByIdInputPort = findUserByIdInputPort;
        this.updateUserOutputPort = updateUserOutputPort;
        this.savePaymentOutputPort = savePaymentOutputPort;
        this.sendToKafkaOutputPort = sendValidatedPaymentOutputPort;
    }

    @Override
    public void payment(Sale aSale){
        try{
            var user = findUserByIdInputPort.find(aSale.getUserId());
            if(user.getBalance().compareTo(aSale.getValue()) < 0){
                throw new RuntimeException("Insufficient balance.");
            }
            user.debitBalance(aSale.getValue());
            updateUserOutputPort.update(user);
            savePaymentOutputPort.save(buildPayment(aSale));
            sendToKafkaOutputPort.send(aSale, SaleEvent.VALIDATED_PAYMENT);
        }catch (Exception e){
            log.error("There's a error = {}", e.getMessage());
            sendToKafkaOutputPort.send(aSale, SaleEvent.FAILED_PAYMENT);
        }
    }

    private Payment buildPayment(Sale aSale){
        return new Payment(null, aSale.getUserId(), aSale.getId(), aSale.getValue());
    }
}
