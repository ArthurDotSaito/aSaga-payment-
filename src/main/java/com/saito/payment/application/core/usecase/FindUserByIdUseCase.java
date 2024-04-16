package com.saito.payment.application.core.usecase;

import com.saito.payment.application.core.domain.User;
import com.saito.payment.application.ports.out.FindUserByIdOutputPort;

public class FindUseByIdUseCase {

    private final FindUserByIdOutputPort findUserByIdOutputPort;

    public FindUseByIdUseCase(FindUserByIdOutputPort findUserByIdOutputPort){
        this.findUserByIdOutputPort = findUserByIdOutputPort;
    }

    public User find(final Integer anId){
        return findUserByIdOutputPort.find(anId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
