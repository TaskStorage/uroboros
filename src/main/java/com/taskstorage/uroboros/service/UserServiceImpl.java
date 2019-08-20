package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.Role;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> selectAll() {
        return userRepository.selectAll();
    }

    @Override
    public User selectById(Long id) {
        return userRepository.selectById(id);
    }

    @Override
    public User selectByUsername(String username) {
        return userRepository.selectByUsername(username);
    }

    @Override
    public void createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.createUser(user);
    }

    @Override
    public void updateUser(User user) {
        //TODO + view
        userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        //TODO + view (disable)
        userRepository.deleteUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.selectByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
