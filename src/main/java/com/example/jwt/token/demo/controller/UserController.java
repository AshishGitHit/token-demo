package com.example.jwt.token.demo.controller;

import com.example.jwt.token.demo.entity.MyUser;
import com.example.jwt.token.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRolesAndUsers() {
        userService.initRolesAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public MyUser registerNewUser(@RequestBody MyUser myUser) {
        return userService.registerNewUser(myUser);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "This URL is accessible only to admin.";
    }

    @GetMapping({"/forAll"})
    @PreAuthorize("hasAnyRole('Admin', 'User')")
    public String forUser() {
        return "This URL is accessible to all.";
    }
}
