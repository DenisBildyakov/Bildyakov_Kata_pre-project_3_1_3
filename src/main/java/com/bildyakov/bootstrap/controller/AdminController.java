package com.bildyakov.bootstrap.controller;

import com.bildyakov.bootstrap.model.Role;
import com.bildyakov.bootstrap.model.User;
import com.bildyakov.bootstrap.service.RoleService;
import com.bildyakov.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/admin")
    public String openStartPage(ModelMap model, Principal principal) {
        List<User> users = userService.findAllUsers();
        List<Role> roles = roleService.findAllRoles();
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("meUser", user);
        model.addAttribute("roles", roles);
        return "/admin";
    }

    @GetMapping("/admin/addUser")
    public String addUser(Model model, Principal principal) {
        List<Role> roles = roleService.findAllRoles();
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("meUser", user);
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "/addUser";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/admin";
    }


    @PatchMapping("/admin/edit/{id}")
    public String updateUser(Model model, @ModelAttribute("editUser") User user, @PathVariable(value = "id") long id) {
        model.addAttribute("editUser", userService.findUserById(id));
//
//        if (user.getPassword() == null ||
//                user.getPassword().equals("") || user.getPassword().equals(userService.findUserById(id).getPassword())) {
//            user.setPassword(userService.findUserById(id).getPassword());
//        } else {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }

        userService.editUser(user, id);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}
