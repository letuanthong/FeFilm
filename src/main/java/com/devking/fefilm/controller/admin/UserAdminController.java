package com.devking.fefilm.controller.admin;


import com.devking.fefilm.model.Country;
import com.devking.fefilm.model.User;
import com.devking.fefilm.service.UserService;
import com.devking.fefilm.service.Users_RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserAdminController {
    @Autowired
    UserService userService;
    @Autowired
    Users_RolesService userServiceRole;
    @GetMapping("/users")
    public ModelAndView getAllUser(Model model) {
        ModelAndView modelAndView = new ModelAndView("/admin/users");
        model.addAttribute("users",userService.getAllUser());
        return modelAndView;
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userServiceRole.removeUsersRoleById(id);
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }

}

