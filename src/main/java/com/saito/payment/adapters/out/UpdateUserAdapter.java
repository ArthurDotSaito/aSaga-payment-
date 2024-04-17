package com.saito.payment.adapters.out;

import com.saito.payment.adapters.out.mapper.UserEntityMapper;
import com.saito.payment.adapters.out.repository.UserRepository;
import com.saito.payment.application.core.domain.User;
import com.saito.payment.application.ports.out.UpdateUserOutputPort;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateUserAdapter implements UpdateUserOutputPort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public void update(User user) {
        var userEntity = userEntityMapper.toUserEntity(user);
        userRepository.save(userEntity);
    }
}
