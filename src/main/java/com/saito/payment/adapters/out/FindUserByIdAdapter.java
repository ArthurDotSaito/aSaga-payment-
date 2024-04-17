package com.saito.payment.adapters.out;

import com.saito.payment.adapters.out.mapper.UserEntityMapper;
import com.saito.payment.adapters.out.repository.UserRepository;
import com.saito.payment.adapters.out.repository.entity.UserEntity;
import com.saito.payment.application.core.domain.User;
import com.saito.payment.application.ports.out.FindUserByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindUserByIdAdapter implements FindUserByIdOutputPort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> find(Integer anId) {
        var userEntity = userRepository.findById(anId);
        return userEntity.map(userEntityMapper::toUser);
    }
}
