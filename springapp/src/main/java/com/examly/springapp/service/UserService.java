package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.User;

public interface UserService {

    User createUser(User user);

    User loginUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

}