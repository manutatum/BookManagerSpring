package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface UserService {

    UserResponseDto findByUsername(String username);

    UserResponseDto saveUser(UserRequestDto dto);

    Page<User> findAllUsers(Pageable pageable);

    UserResponseDto deleteUserById(Long id);
}
