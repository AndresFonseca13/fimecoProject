package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.RoleDTO;
import com.fimeco.fimeco.infra.services.UserDetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailServiceImpl userDetailService;

    public AdminController(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @PostMapping("/add_Role")
    public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO){
        System.out.println(roleDTO.username() + " " + roleDTO.rol());
        return userDetailService.addRole(roleDTO.username(), roleDTO.rol());
    }

    @PostMapping("/remove_Role")
    public ResponseEntity<?> removeRole(@RequestBody RoleDTO body){
        return userDetailService.removeRole(body.username(), body.rol());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        return userDetailService.getUser(username);
    }

}
