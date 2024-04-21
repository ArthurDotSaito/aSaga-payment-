package com.saito.payment.config.useCase;

import com.saito.payment.adapters.out.SavePaymentAdapter;
import com.saito.payment.adapters.out.SendValidatedPaymentAdapter;
import com.saito.payment.adapters.out.UpdateUserAdapter;
import com.saito.payment.application.core.usecase.FindUserByIdUseCase;
import com.saito.payment.application.core.usecase.SalePaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalePaymentConfig {

    @Bean
    public SalePaymentUseCase salePaymentUseCase(
            FindUserByIdUseCase findUserByIdUseCase,
            UpdateUserAdapter updateUserAdapter,
            SavePaymentAdapter savePaymentAdapter,
            SendValidatedPaymentAdapter sendValidatedPaymentAdapter
    ){
        return new SalePaymentUseCase(findUserByIdUseCase, updateUserAdapter, savePaymentAdapter, sendValidatedPaymentAdapter);
    }
}
