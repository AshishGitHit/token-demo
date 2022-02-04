package com.example.jwt.token.demo.service;

import com.example.jwt.token.demo.entity.MyRole;
import com.example.jwt.token.demo.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    public RoleRepo roleRepo;

    public MyRole createNewRole(MyRole myRole) {
        return roleRepo.save(myRole);
    }
}
