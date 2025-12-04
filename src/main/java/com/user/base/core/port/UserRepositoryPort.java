package com.user.base.core.port;

import com.user.base.core.model.User;

public interface UserRepositoryPort {

    Boolean Adduser(User user);
    User finduser(String username, String password);
    User Updateuser(User user);
    boolean Deleteuser(String username,String password);


}
