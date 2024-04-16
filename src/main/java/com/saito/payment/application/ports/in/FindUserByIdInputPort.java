package com.saito.payment.application.ports.in;

import com.saito.payment.application.core.domain.User;

public interface FindUserByIdInputPort {
    public User find(final Integer anId);
}
