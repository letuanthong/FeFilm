package com.devking.fefilm.controller;

import com.devking.fefilm.model.Role;
import com.devking.fefilm.model.User;
import com.devking.fefilm.model.Users_Roles;
import com.devking.fefilm.service.RoleService;
import com.devking.fefilm.service.UserService;
import com.devking.fefilm.service.Users_RolesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private Users_RolesService usersRolesService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("emailExists", false);
        return modelAndView;
    }
    @PostMapping("/register")
    public String registerPost(@ModelAttribute User userModel, @RequestParam("confirm") String confirm, Model model) throws ServletException {
        if (userService.emailExists(userModel.getEmail())) {
            model.addAttribute("emailExists", true);
            return "register"; // Trả về trang đăng ký với thông báo lỗi
        }
        if (Objects.equals(confirm, userModel.getPassword())){
            String password = userModel.getPassword();
            userModel.setPassword(bCryptPasswordEncoder.encode(password));
            Role role = roleService.findRoleByName("USER");
            Users_Roles usersRoles = new Users_Roles();
            usersRoles.setRole(role);
            userService.save(userModel);
            usersRoles.setUser(userService.getUserByEmail(userModel.getEmail()).get());
            usersRolesService.save(usersRoles);
            return "redirect:/login";
        }else{
            return "redirect:/register";
        }

    }
    @GetMapping("/forgot")
    public ModelAndView forgot() {
        ModelAndView modelAndView = new ModelAndView("forgot");
        return modelAndView;
    }
}
