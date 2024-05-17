package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.user.EmailDTO;
import com.fimeco.fimeco.infra.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/send")
    public ResponseEntity<?> receiveRequestEmail(@RequestBody EmailDTO emailDTO) {

        emailService.sendEmail(emailDTO.to(), emailDTO.subject(), emailDTO.message());

        return ResponseEntity.ok("Email sent");
    }
}
