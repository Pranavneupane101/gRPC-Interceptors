package com.user.base.application.mapper;


import com.gRPC.User.UsergRPC;
import com.user.base.application.persistence.entity.UserEntity;
import com.user.base.core.model.User;

public class UserAndUsergRPCMapper  {
    public static UsergRPC toUsergRPC(User user){

        return UsergRPC.newBuilder().setId(user.getId())
                .setFname(user.getFirstName())
                .setLname(user.getLastName())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .build();
    }

    public static User toUser(UsergRPC usergRPC){
        User user = new User();

        user.setId(usergRPC.getId());
        user.setFirstName(usergRPC.getFname());
        user.setLastName(usergRPC.getLname());
        user.setEmail(usergRPC.getEmail());
        user.setUsername(usergRPC.getUsername());
        user.setPassword(usergRPC.getPassword());
        return user;
    }





}
