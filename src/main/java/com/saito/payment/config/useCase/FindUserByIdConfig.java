package com.saito.payment.config.useCase;

import com.saito.payment.adapters.out.FindUserByIdAdapter;
import com.saito.payment.application.core.usecase.FindUserByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindUserByIdConfig {

    @Bean
    public FindUserByIdUseCase findUserByIdUseCase(FindUserByIdAdapter findUserByIdAdapter){
        return new FindUserByIdUseCase(findUserByIdAdapter);
    }

}
