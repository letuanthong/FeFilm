package com.devking.fefilm.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView("/admin/adminPage");
        return modelAndView;
    }
}
