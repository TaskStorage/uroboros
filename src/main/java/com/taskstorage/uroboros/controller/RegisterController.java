package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Role;
import com.taskstorage.uroboros.model.Task;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.repository.UserRepository;
import com.taskstorage.uroboros.service.TaskService;
import com.taskstorage.uroboros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(Model model, User user) {
        userService.createUser(user);
        return "redirect:/";
    }

}
