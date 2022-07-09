package com.bildyakov.bootstrap.controller;

import com.bildyakov.bootstrap.model.Role;
import com.bildyakov.bootstrap.model.User;
import com.bildyakov.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Controller
public class IndexController {

    private final UserService userService;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IndexController(UserService userService, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value ="/")
    public String openStartPage() {
        List<User> users = userService.findAllUsers();

        if (users.isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");
            Collection<Role> adminRole = new HashSet<>();
            Collection<Role> userRole = new HashSet<>();
            Collection<Role> anyRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            anyRole.add(admin);
            anyRole.add(user);
            userService.addUser(new User("Maha", "Smirnova", 33, "admin@mail.ru", bCryptPasswordEncoder.encode("admin@mail.ru"), adminRole));
            userService.addUser(new User("Misha", "Krokodilov", 24, "user@mail.ru", bCryptPasswordEncoder.encode("user@mail.ru"), userRole));
            userService.addUser(new User("Dima", "Borisov", 18, "dimab@mail.ru", bCryptPasswordEncoder.encode("dimab@mail.ru"), userRole));
            userService.addUser(new User("Vasya", "Pupkin", 16, "vasyap@mail.ru", bCryptPasswordEncoder.encode("vasyap@mail.ru"), userRole));
            userService.addUser(new User("Kostya", "Gradov", 52, "kostyag@mail.ru", bCryptPasswordEncoder.encode("kostyag@mail.ru"), anyRole));
        }
        return "redirect:/login";
    }
}
