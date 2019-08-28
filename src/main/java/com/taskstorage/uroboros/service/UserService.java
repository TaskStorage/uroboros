package com.taskstorage.uroboros.service;

import com.taskstorage.uroboros.model.Role;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@PropertySource("classpath:application.properties")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${hostname}")
    private String hostname;

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
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.createUser(user);

        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            "Welcome to Task Storage. Please, visit next link: http://%s/activate/%s",
                    user.getUsername(),
                    hostname,
                    user.getActivationCode()
            );
            mailService.send(user.getEmail(),"Activation code", message);
        }
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

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepository.updateUser(user);

        return true;
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email !=null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
                user.setActive(false);
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.updateUser(user);
        if (isEmailChanged) {
            sendMessage(user);
        }

    }
}
