package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> selectAll();
    User selectById(Long id);
    User selectByUsername(String username);
    boolean createUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
