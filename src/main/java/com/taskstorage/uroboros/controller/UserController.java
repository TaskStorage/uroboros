package com.taskstorage.uroboros.controller;

import com.taskstorage.uroboros.model.Role;
import com.taskstorage.uroboros.model.User;
import com.taskstorage.uroboros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.selectAll();
        model.addAttribute("users", users);
        return "users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/edit/{id}")
    public String getUserPage(Model model, @PathVariable Long id) {

        User user = userService.selectById(id);

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEditPage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users/edit/{id}")
    public String userSave(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam(required = false) String active,
            @RequestParam Map<String, String> form,
            @PathVariable Long id) {

        User user = userService.selectById(id);
        if (username != null) {
            user.setUsername(username);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (active != null && active.equals("on")) {
            user.setActive(true);
        } else {
            user.setActive(false);
        }
        //Список допустимых ролей
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        //Очищаем существующие роли
        user.getRoles().clear();
        //Вытаскиваем все данные из формы и если они совпадают с именами ролей - записываем
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.updateUser(user);

        return "redirect:/users";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Model model,
                                @AuthenticationPrincipal User user,
                                @RequestParam String password,
                                @RequestParam String email) {
        userService.updateProfile(user, password, email);
        SecurityContextHolder.clearContext();
        model.addAttribute("message", "Вы успешно изменили данные, залогиньтесь снова");
        return "login";
    }
}
