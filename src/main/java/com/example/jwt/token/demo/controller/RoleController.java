package com.example.jwt.token.demo.controller;

import com.example.jwt.token.demo.entity.MyRole;
import com.example.jwt.token.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createNewRole")
    public MyRole createNewRole(@RequestBody MyRole myRole) {
        return roleService.createNewRole(myRole);
    }
}
