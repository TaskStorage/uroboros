package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();
    User selectById(Long id);
    User selectByUsername(String username);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
