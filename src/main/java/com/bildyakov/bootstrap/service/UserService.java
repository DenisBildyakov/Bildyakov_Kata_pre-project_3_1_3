package com.bildyakov.bootstrap.service;

import com.bildyakov.bootstrap.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    List<User> findAllUsers();

    public void removeUserById(long id);

    public User findUserById(long id);

    public void updateUser(long id, User user);

    public User findByEmail (String email);
}
