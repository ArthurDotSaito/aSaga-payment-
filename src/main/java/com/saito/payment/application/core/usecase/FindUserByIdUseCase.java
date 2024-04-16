package com.saito.payment.application.core.usecase;

import com.saito.payment.application.core.domain.User;
import com.saito.payment.application.ports.in.FindUserByIdInputPort;
import com.saito.payment.application.ports.out.FindUserByIdOutputPort;

public class FindUserByIdUseCase implements FindUserByIdInputPort {

    private final FindUserByIdOutputPort findUserByIdOutputPort;

    public FindUserByIdUseCase(FindUserByIdOutputPort findUserByIdOutputPort){
        this.findUserByIdOutputPort = findUserByIdOutputPort;
    }

    @Override
    public User find(final Integer anId){
        return findUserByIdOutputPort.find(anId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
