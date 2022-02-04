package com.example.jwt.token.demo.controller;

import com.example.jwt.token.demo.entity.JwtRequest;
import com.example.jwt.token.demo.entity.JwtResponse;
import com.example.jwt.token.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }
}
