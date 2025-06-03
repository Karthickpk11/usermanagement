package com.usermanged.usermanagement.service;

import com.usermanged.usermanagement.model.User;
import com.usermanged.usermanagement.repository.UserRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepositor userRepositor;

    public List<User> getAllUsers() {
        return userRepositor.findAll();
    }

    public User getUserById(Long id) {
        return userRepositor.findById(id).get();
    }

    public User addUser(User user) {
        return userRepositor.save(user);
    }

    public User updateUser(User user) {
        return userRepositor.save(user);
    }

    public void deleteUser(Long id) {
        userRepositor.deleteById(id);
    }

}
