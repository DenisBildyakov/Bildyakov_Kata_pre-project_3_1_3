package com.bildyakov.bootstrap.service;


import com.bildyakov.bootstrap.model.User;
import com.bildyakov.bootstrap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(long id, User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
