package com.fimeco.fimeco.infra.services;

import com.fimeco.fimeco.domain.Role.RolRepository;
import com.fimeco.fimeco.domain.Role.Role;
import com.fimeco.fimeco.domain.Role.RoleEnum;
import com.fimeco.fimeco.domain.user.*;
import com.fimeco.fimeco.infra.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;


    private final RolRepository roleRepository;

    public UserDetailServiceImpl(UserRepository userRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, RolRepository roleRepository) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userEntity.getRoles().stream()
                .flatMap(role -> role.getPermissionsList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isCredentialsNoExpired(),
                userEntity.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse login(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.usernameOrEmail();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        System.out.println(username);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accesToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, "user logged successfully", accesToken, true);
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest){
        String username = authCreateUserRequest.username();
        String password = authCreateUserRequest.password();
        String email = authCreateUserRequest.email();
        String firstName = authCreateUserRequest.firstName();
        String lastName = authCreateUserRequest.lastName();
        String phone = authCreateUserRequest.phone();
        List<String> roleRequest = authCreateUserRequest.roleRequest().roleListName();

        Set<Role> roleEntitySet = new HashSet<>(roleRepository.findRoleEntitiesByRoleEnumIn(roleRequest));

        if (roleEntitySet.isEmpty()){
            throw new IllegalArgumentException("Roles not found");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .roles(roleEntitySet)
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .build();

        return saveUserEntityAndCreateToken(userEntity);
    }

    public ResponseEntity<?> addRole(String username, String role){
        UserEntity user = userRepository.findUserEntityByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Role roleAdd = roleRepository.findRoleByRoleEnumIn(Set.of(RoleEnum.valueOf(role))).orElseThrow(() -> new IllegalArgumentException("Role not found: " + role));
        user.addRole(roleAdd);
        userRepository.save(user);
        return ResponseEntity.ok().body("New Role added to user");
    }

    public ResponseEntity<?> removeRole(String username, String role){
        UserEntity user = userRepository.findUserEntityByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        Role roleRemove = roleRepository.findRoleByRoleEnumIn(Set.of(RoleEnum.valueOf(role))).orElseThrow(() -> new IllegalArgumentException("Role not found: " + role));
        user.removeRole(roleRemove);
        userRepository.save(user);
        return ResponseEntity.ok().body("Role removed from user");
    }

    public ResponseEntity<UserEntity> getUser(String username){
        UserEntity userEntity = userRepository.findUserEntityByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return ResponseEntity.ok().body(userEntity);
    }

    public AuthResponse createNormalUSer(CreateUserRequest createUserRequest){
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByRoleEnumIn(Set.of(RoleEnum.CLIENT)).orElseThrow(() -> new IllegalArgumentException("Role not found: " + RoleEnum.CLIENT)));
        UserEntity userEntity = UserEntity.builder()
                .username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email())
                .firstName(createUserRequest.firstName())
                .lastName(createUserRequest.lastName())
                .phone(createUserRequest.phone())
                .roles(roles)
                .isEnabled(true)
                .accountNoExpired(true)
                .accountNoLocked(true)
                .credentialsNoExpired(true)
                .build();

        return saveUserEntityAndCreateToken(userEntity);
    }

    private AuthResponse saveUserEntityAndCreateToken(UserEntity userEntity) {
        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name())));
            if (role.getPermissionsList() != null) {
                role.getPermissionsList()
                        .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));
            }
        });

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getUsername(), userCreated.getPassword(), authorityList);

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(userEntity.getUsername(), "User created successfully", accessToken, true);
    }

}
