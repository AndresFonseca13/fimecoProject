package com.fimeco.fimeco.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByAuthority(String authority);
}
