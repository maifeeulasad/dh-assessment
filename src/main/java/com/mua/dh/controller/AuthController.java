package com.mua.dh.controller;

import com.mua.dh.model.LoginCredential;
import com.mua.dh.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/join")
    public Boolean join(@RequestBody LoginCredential loginCredential){
        return service.join(loginCredential);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginCredential loginCredential){
        return service.login(loginCredential);
    }

}
