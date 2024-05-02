package com.fimeco.fimeco;

import com.fimeco.fimeco.domain.Role.RolRepository;
import com.fimeco.fimeco.domain.Role.Role;
import com.fimeco.fimeco.domain.user.User;
import com.fimeco.fimeco.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class FimecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FimecoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RolRepository rolRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if (rolRepository.findByAuthority("ADMIN").isPresent()) return;
			Role adminRole = rolRepository.save(new Role("ADMIN"));
			rolRepository.save(new Role("USER"));
			rolRepository.save(new Role("CLIENTE"));
			rolRepository.save(new Role("EMPLEADO"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);

			User admin = new User(1, "admin", passwordEncoder.encode("password"), roles);

			userRepository.save(admin);
		};
	}

}
