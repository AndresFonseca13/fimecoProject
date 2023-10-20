package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.LoginResponseDTO;
import com.fimeco.fimeco.domain.user.RegistrationDTO;
import com.fimeco.fimeco.domain.user.User;
import com.fimeco.fimeco.infra.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/register_customer")
    public User registerCustomer(@RequestBody RegistrationDTO body){
        return authenticationService.registerCustomer(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
