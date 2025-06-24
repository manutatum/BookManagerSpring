package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;

public interface UserService {

    UserResponseDto saveUser(UserRequestDto dto);

}
