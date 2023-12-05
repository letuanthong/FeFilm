package com.devking.fefilm.controller.admin;


import com.devking.fefilm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ModelAndView getAllUser(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/users");
        model.addAttribute("users",userService.getAllUser());
        return modelAndView;
    }

}

