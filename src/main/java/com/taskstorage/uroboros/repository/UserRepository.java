package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.User;

import java.util.List;

public interface UserRepository {
    List<User> selectAll();
    User selectById(Long id);
    User selectByUsername(String username);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
