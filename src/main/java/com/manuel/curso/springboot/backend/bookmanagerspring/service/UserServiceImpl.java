package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Role;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.ERole;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.RoleRepository;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto findByUsername(String username) {

        User user = userRepository.findByUsername(username).orElseThrow( () -> new NoSuchElementException("User with username " + username + " not found!") );

        return new UserResponseDto(user);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto dto) {

        //? COMPROBAMOS QUE EL REPAS Y LA PASS SEAN IGUALES, QUE NO EXISTA USUARIO CON ESE NOMBRE
        if ( !dto.getPassword().equals(dto.getRePassword()) ) throw new IllegalArgumentException("Passwords don't match");

        if ( userRepository.existsByUsername(dto.getUsername()) ) throw new IllegalArgumentException("Username already exists");

        //? COGEMOS EL ROLE
        Role role = roleRepository.findByName(ERole.ROLE_USER).orElseThrow( () -> new IllegalArgumentException("Role not found"));

        User user = new User();

        user.setUsername(dto.getUsername());

        //? ENCRIPTAMOS LA CONTRASEÑA
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        user.setRoles(Collections.singletonList(role));

        return new UserResponseDto(userRepository.save(user));

    }

}
