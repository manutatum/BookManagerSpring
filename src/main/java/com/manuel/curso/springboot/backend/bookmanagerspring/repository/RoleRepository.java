package com.manuel.curso.springboot.backend.bookmanagerspring.repository;


import com.manuel.curso.springboot.backend.bookmanagerspring.model.Role;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
