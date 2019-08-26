package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.Role;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> selectAll() {
        return userRepository.selectAll();
    }

    public User selectById(Long id) {
        return userRepository.selectById(id);
    }

    public User selectByUsername(String username) {
        return userRepository.selectByUsername(username);
    }


    public boolean createUser(User user) {

        User userFromDB = selectByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        userRepository.createUser(user);
        return true;
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(Long id) {
        //TODO + view (disable)
        userRepository.deleteUser(id);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.selectByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
