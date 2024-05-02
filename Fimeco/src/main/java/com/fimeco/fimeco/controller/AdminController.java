package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.RoleDTO;
import com.fimeco.fimeco.infra.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add_Role")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO body){
        return authenticationService.addRole(body.username(), body.rol());
    }

    @PostMapping("/remove_Role")
    public ResponseEntity<?> removeRole(@RequestBody RoleDTO body){
        return authenticationService.removeRole(body.username(), body.rol());
    }

}
