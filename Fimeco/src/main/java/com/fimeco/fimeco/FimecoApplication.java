package com.fimeco.fimeco;

import com.fimeco.fimeco.domain.Role.Role;
import com.fimeco.fimeco.domain.Role.RoleEnum;
import com.fimeco.fimeco.domain.permission.Permission;
import com.fimeco.fimeco.domain.user.UserEntity;
import com.fimeco.fimeco.domain.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class FimecoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FimecoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return args -> {
			Permission createPermission = Permission.builder()
					.name("CREATE")
					.build();

			Permission readPermission = Permission.builder()
					.name("READ")
					.build();

			Permission updatePermission = Permission.builder()
					.name("UPDATE")
					.build();

			Permission deletePermission = Permission.builder()
					.name("DELETE")
					.build();

			Permission refactorPermission = Permission.builder()
					.name("REFACTOR")
					.build();

//            Create Roles
			Role roleAdmin = Role.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Role roleUSer = Role.builder()
					.roleEnum(RoleEnum.USER)
					.permissionsList(Set.of(createPermission, readPermission))
					.build();

			Role roleGuest = Role.builder()
					.roleEnum(RoleEnum.GUEST)
					.permissionsList(Set.of(readPermission))
					.build();

			Role roleDeveloper = Role.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			Role roleWorker = Role.builder()
					.roleEnum(RoleEnum.WORKER)
					.permissionsList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			Role roleClient = Role.builder()
					.roleEnum(RoleEnum.CLIENT)
					.permissionsList(Set.of(createPermission, readPermission))
					.build();

			// Create Users
			UserEntity userAndres = UserEntity.builder()
					.username("andres")
					.password("$2a$10$60X1mUo/FnktJNOYSAQBCOtp.1HJwjiXdLnq.0ihbtKp3eSnMa8W.")
					.email("andres@gmail.com")
					.firstName("Andres")
					.lastName("Fonseca")
					.phone("3502957268")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleAdmin, roleClient))
					.build();

			UserEntity userDaniel = UserEntity.builder()
					.username("daniel")
					.password("$2a$10$60X1mUo/FnktJNOYSAQBCOtp.1HJwjiXdLnq.0ihbtKp3eSnMa8W.")
					.email("daniel@gmail.com")
					.firstName("Daniel")
					.lastName("Bermudez")
					.phone("35026987268")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleUSer, roleWorker))
					.build();

			UserEntity userAngie = UserEntity.builder()
					.username("angie")
					.password("$2a$10$60X1mUo/FnktJNOYSAQBCOtp.1HJwjiXdLnq.0ihbtKp3eSnMa8W.")
					.email("angie@gmail.com")
					.firstName("Angie")
					.lastName("Carrillo")
					.phone("340396736")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleGuest))
					.build();

			UserEntity userVanessa = UserEntity.builder()
					.username("vanessa")
					.password("$2a$10$60X1mUo/FnktJNOYSAQBCOtp.1HJwjiXdLnq.0ihbtKp3eSnMa8W.")
					.email("vanessa@gmail.com")
					.firstName("Vanessa")
					.lastName("Rodriguez")
					.phone("398098736")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialsNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			userRepository.saveAll(List.of(userAndres, userDaniel, userAngie, userVanessa));
		};
	}

}
