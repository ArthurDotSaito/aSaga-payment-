package com.saito.payment.adapters.out.mapper;

import com.saito.payment.adapters.out.repository.entity.UserEntity;
import com.saito.payment.application.core.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
