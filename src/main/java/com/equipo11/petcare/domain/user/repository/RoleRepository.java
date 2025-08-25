package com.equipo11.petcare.domain.user.repository;

import com.equipo11.petcare.domain.user.entity.Role;
import com.equipo11.petcare.domain.user.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}


