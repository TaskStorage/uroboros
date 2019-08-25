package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String createUser(User user, Model model) {

        if (!userService.createUser(user)){
            model.addAttribute("error", "User already exists");
            return "register";
        }

        return "redirect:/";
    }

}
