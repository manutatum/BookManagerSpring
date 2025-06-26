package com.manuel.curso.springboot.backend.bookmanagerspring.controller;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(dto));
    }

    // TODO: REFRESH TOKEN

}