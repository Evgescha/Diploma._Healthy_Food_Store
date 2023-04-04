package com.hescha.healthystore.controller;

import com.hescha.healthystore.model.User;
import com.hescha.healthystore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginAndRegistrationController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String registrationPost(User user, Model model) {
        boolean success = userService.registerNew(user);
        String response = success
                ? "Success"
                : "Registration failed. Try again later";
        model.addAttribute("success", response);
        return "registration";

    }
}
