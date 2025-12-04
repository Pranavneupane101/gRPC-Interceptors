package com.user.base.application.adaptors;

import com.user.base.application.mapper.UserAndUserEntityMapper;
import com.user.base.application.persistence.dao.UserDao;
import com.user.base.application.persistence.entity.UserEntity;
import com.user.base.core.model.User;
import com.user.base.core.port.UserRepositoryPort;
import jakarta.inject.Inject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class UserRepositoryAdapter implements UserRepositoryPort {
    @Inject
    UserDao userDao;

    @Override
    public Boolean Adduser(User user) {
        try{

            MessageDigest md=MessageDigest.getInstance("SHA-512");
            byte[] digest =md.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
            String hashedPassword = Base64.getEncoder().encodeToString(digest);
            user.setPassword(hashedPassword);
            userDao.save(UserAndUserEntityMapper.toUserEntity(user));
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User finduser(String username, String password) {try{
        MessageDigest md=MessageDigest.getInstance("SHA-512");
        byte[] digest =md.digest(password.getBytes(StandardCharsets.UTF_8));
        String hashedPassword = Base64.getEncoder().encodeToString(digest);
        return UserAndUserEntityMapper.toUser(userDao.findByUsernameAndPassword(username,hashedPassword));

    }
    catch(Exception e){
        e.printStackTrace();
        return null;
    }

    }

    @Override
    public User Updateuser(User user) {
        UserEntity entity=userDao.update(UserAndUserEntityMapper.toUserEntity(user));
        return UserAndUserEntityMapper.toUser(entity) ;
    }

    @Override
    public boolean Deleteuser(String username, String password) {
        try {


            userDao.delete(
                    userDao.findByUsernameAndPassword(username, password)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
