package com.devking.fefilm.controller;

import com.devking.fefilm.model.User;
import com.devking.fefilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/user/profile")
    public String getUserProfile(Model model) {
        User currentUser = userService.getCurrentLoggingUser();
        model.addAttribute("currentUser", currentUser);
        return "user-profile";
    }

    @PostMapping("/user/profile")
    public String updateUserProfile(@ModelAttribute User userModel) {
        String password = userModel.getPassword();
        userModel.setPassword(bCryptPasswordEncoder.encode(password));
        userService.updateUser(userModel);
        return "redirect:/";
    }
}
