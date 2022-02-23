package com.morrislab.httpconcepts.service.user;

import com.morrislab.httpconcepts.model.User;

import java.util.List;

public interface UserService {

    List <User> findAllUsers();

    User findUserById(long id);

    User saveUser(User user);

    User updateUser(User user, long id);

    void deleteUser(long id);
}
