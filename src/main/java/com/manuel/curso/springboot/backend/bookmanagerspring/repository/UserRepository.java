package com.manuel.curso.springboot.backend.bookmanagerspring.repository;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @NonNull
    @EntityGraph(attributePaths = {"books", "roles"})
    Optional<User> findById(@NonNull Long id);

}
