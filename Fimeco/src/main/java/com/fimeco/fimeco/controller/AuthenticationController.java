package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.*;
import com.fimeco.fimeco.infra.services.ResetPasswordService;
import com.fimeco.fimeco.infra.services.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    private final UserDetailServiceImpl userDetailService;

    private final ResetPasswordService resetPasswordService;

    public AuthenticationController(UserDetailServiceImpl userDetailService, ResetPasswordService resetPasswordService) {
        this.userDetailService = userDetailService;
        this.resetPasswordService = resetPasswordService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUserRequest){
        return new ResponseEntity<>(this.userDetailService.createUser(authCreateUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/register-customer")
    public AuthResponse registerCustomer(@RequestBody CreateUserRequest body){
        return userDetailService.createNormalUSer(body);
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
        return new ResponseEntity<>(this.userDetailService.login(userRequest), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO){
        resetPasswordService.resetPasswordRequest(forgetPasswordDTO);
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordDTO resetPasswordDTO){
        System.out.println("token: " + token);
        resetPasswordService.resetPassword(token, resetPasswordDTO.newPassword());
        return ResponseEntity.ok("Password reset");
    }


}
