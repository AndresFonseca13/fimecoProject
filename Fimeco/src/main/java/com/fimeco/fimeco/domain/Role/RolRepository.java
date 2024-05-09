package com.fimeco.fimeco.domain.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {

    List<Role> findRoleEntitiesByRoleEnumIn(List<String> roleNames);

    Optional<Role> findRoleByRoleEnumIn(Collection<RoleEnum> roleEnum);

}
