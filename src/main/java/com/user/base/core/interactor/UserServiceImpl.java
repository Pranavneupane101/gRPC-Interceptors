package com.user.base.core.interactor;

import com.user.base.core.model.User;
import com.user.base.core.port.UserRepositoryPort;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService{
    @Inject
    UserRepositoryPort repository;

    @Override
    public Boolean AddUser(User user) {
        try{
            repository.Adduser(user);
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public User findUser(String username, String password) {
        return repository.finduser(username,password);

    }

    @Override
    public User UpdateUser(User user) {
        return repository.Updateuser(user);
    }

    @Override
    public boolean DeleteUser(String username, String password) {
        return  repository.Deleteuser(username,password);
    }


}
