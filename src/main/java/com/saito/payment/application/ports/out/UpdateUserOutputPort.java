package com.saito.payment.application.ports.out;

import com.saito.payment.application.core.domain.User;

public interface UpdateUserOutputPort {

    void update(User user);
}
