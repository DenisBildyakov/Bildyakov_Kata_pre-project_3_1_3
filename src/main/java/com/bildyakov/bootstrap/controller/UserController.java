package com.bildyakov.bootstrap.controller;

import com.bildyakov.bootstrap.model.User;
import com.bildyakov.bootstrap.service.RoleService;
import com.bildyakov.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value ="/user")
    public String showUserInfo(ModelMap model, Principal principal) {
        User user = userService.findByEmail(principal.getName());

        model.addAttribute("user", user);
        return "/user";
    }
}
