package com.fimeco.fimeco.infra.services;

import com.fimeco.fimeco.domain.Role.RolRepository;
import com.fimeco.fimeco.domain.Role.Role;
import com.fimeco.fimeco.domain.user.LoginResponseDTO;
import com.fimeco.fimeco.domain.user.User;
import com.fimeco.fimeco.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenServices;

    public ResponseEntity<?> registerUser(String username, String password){

        if (userRepository.findByUsername(username).isPresent()){
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(null, null,null, null, "User already exists");
            return ResponseEntity.badRequest().body(loginResponseDTO.getMessage());
        }

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        User user = new User(username, encodedPassword, authorities);
        userRepository.save(user);

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenServices.generateJwt(auth);

            return ResponseEntity.ok().body(new LoginResponseDTO(user.getUserId(), user.getUsername(), (Set<?>) user.getAuthorities(), token, "User authenticated successfully"));

        } catch(AuthenticationException e){
            return ResponseEntity.badRequest().body("Error authenticating user");
        }
    }

    public User registerCustomer(String username, String password){

        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("CLIENTE").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(username, encodedPassword, authorities));
    }

    public ResponseEntity<?> loginUser(String username, String password){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenServices.generateJwt(auth);
            User user = userRepository.findByUsername(username).get();

            return ResponseEntity.ok().body(new LoginResponseDTO(user.getUserId(), user.getUsername(), (Set<?>) user.getAuthorities(), token, "User authenticated successfully"));

        } catch(AuthenticationException e) {
            return ResponseEntity.badRequest().body("Error authenticating user, check credentials");
        }
    }
}
