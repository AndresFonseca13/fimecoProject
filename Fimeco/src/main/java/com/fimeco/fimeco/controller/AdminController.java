package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.RoleDTO;
import com.fimeco.fimeco.infra.services.AuthenticationService;
import com.fimeco.fimeco.infra.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/add_Role")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO body){
        return authenticationService.addRole(body.username(), body.rol());
    }

    @PostMapping("/remove_Role")
    public ResponseEntity<?> removeRole(@RequestBody RoleDTO body){
        return authenticationService.removeRole(body.username(), body.rol());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        UserDetails userDetails = userService.loadUserByUsername(username);
        return ResponseEntity.ok().body(userDetails);
    }

}
