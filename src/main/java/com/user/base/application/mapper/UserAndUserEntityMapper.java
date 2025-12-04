package com.user.base.application.mapper;

import com.user.base.application.persistence.entity.UserEntity;
import com.user.base.core.model.User;

public class UserAndUserEntityMapper {

    public static User toUser(UserEntity entity){
        User user = new User();
        user.setId(entity.getId());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setUsername(entity.getUsername());
        return user;
    }

    public static UserEntity toUserEntity(User user){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setUsername(user.getUsername());
        return userEntity;
    }
}
