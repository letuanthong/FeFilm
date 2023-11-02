package com.devking.fefilm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginPageController {
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }
    @GetMapping("/forgot")
    public ModelAndView forgot() {
        ModelAndView modelAndView = new ModelAndView("forgot");
        return modelAndView;
    }
}
