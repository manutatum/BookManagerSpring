package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface UserService {

    List<User> findAllUsers();

    UserResponseDto disabledUser(@PathVariable Long id);

    UserResponseDto enabledUser(@PathVariable Long id);

    UserResponseDto findByUsername(String username);

    UserResponseDto saveUser(UserRequestDto dto);

    Page<User> findAllUsersPaginated(Pageable pageable);

    UserResponseDto deleteUserById(Long id);
}
