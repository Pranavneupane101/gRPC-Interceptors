package com.user.base.core.interactor;

import com.user.base.core.model.User;

public interface UserService {

    Boolean AddUser(User user);
    User findUser(String username, String password);
    User UpdateUser(User user);
    boolean DeleteUser(String username ,String password);




}
