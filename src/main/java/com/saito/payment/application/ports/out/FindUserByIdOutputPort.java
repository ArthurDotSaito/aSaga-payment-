package com.saito.payment.application.ports.out;

import com.saito.payment.application.core.domain.User;

import java.util.Optional;

public interface FindUserByIdOutputPort {

    Optional<User> find(Integer anId);
}
