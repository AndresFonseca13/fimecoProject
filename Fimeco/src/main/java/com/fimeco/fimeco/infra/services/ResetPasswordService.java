package com.fimeco.fimeco.infra.services;

import com.fimeco.fimeco.domain.user.ForgetPasswordDTO;
import com.fimeco.fimeco.domain.user.UserEntity;
import com.fimeco.fimeco.domain.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordService {

    private final EmailServiceImpl emailService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public ResetPasswordService(EmailServiceImpl emailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void resetPasswordRequest(ForgetPasswordDTO forgetPasswordDTO){
        Optional<UserEntity> user = userRepository.findByEmail(forgetPasswordDTO.email());

        if(user.isPresent()){
            String token = UUID.randomUUID().toString();
            UserEntity userEntity = user.get();
            userEntity.setToken(token);
            userRepository.save(userEntity);

            emailService.sendEmail(userEntity.getEmail(), "Reset Password", "To reset your password click on the link: http://localhost:8080/auth/reset-password?token=" + token);
        }
    }

    public void resetPassword(String token, String newPassword){
        Optional<UserEntity> user = userRepository.findUserEntityByToken(token);

        if(user.isPresent()){
            UserEntity userEntity = user.get();
            userEntity.setPassword(passwordEncoder.encode(newPassword));
            userEntity.setToken(null);
            userRepository.save(userEntity);
            userEntity.setToken(null);
        }


    }

}
