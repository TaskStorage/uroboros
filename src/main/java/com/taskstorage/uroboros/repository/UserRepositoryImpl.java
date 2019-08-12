package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectById(Long id) {
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        return null;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
