package com.manuel.curso.springboot.backend.bookmanagerspring.controller;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth", description = "Operaciones de autenticación")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Registrar un usuario",
            description = "Registra un nuevo usuario en el sistema, el cual no puede estar duplicado"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos, Usuario ya existente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(dto));
    }

    // TODO: REFRESH TOKEN

}