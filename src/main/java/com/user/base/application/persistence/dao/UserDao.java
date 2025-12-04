package com.user.base.application.persistence.dao;

import com.user.base.application.persistence.entity.UserEntity;
import com.user.base.core.model.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository
public interface UserDao extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByUsernameAndPassword(String username,String password);
    public UserEntity findByUsername(String username);



}
