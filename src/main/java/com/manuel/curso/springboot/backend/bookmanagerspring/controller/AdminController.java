package com.manuel.curso.springboot.backend.bookmanagerspring.controller;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.PageResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.BookService;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.PdfService;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/admin")
@Hidden
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PdfService pdfService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<PageResponseDto<User>> listUsuarios(@Parameter(hidden = true) Pageable pageable) {

        Page<User> page = userService.findAllUsersPaginated(pageable);

        return ResponseEntity.ok().body(new PageResponseDto<>(page));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/books")
    public ResponseEntity<PageResponseDto<Book>> listBooks(@Parameter(hidden = true) Pageable pageable) {

        Page<Book> page = bookService.findAllAdmin(pageable);

        return ResponseEntity.ok().body(new PageResponseDto<>(page));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> delete(@PathVariable Long id) {

        UserResponseDto userDto = userService.deleteUserById(id);

        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/users/export/pdf")
    public ResponseEntity<Resource> exportUsersToPdf() {

        List<User> users = userService.findAllUsers();

        ByteArrayInputStream pdfStream = pdfService.exportUsersToPdf(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=usuarios.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }

    @GetMapping("users/disabled/{id}")
    public ResponseEntity<?> disabledUser(@PathVariable Long id) {

        UserResponseDto userDto = userService.disabledUser(id);

        return ResponseEntity.ok().body(Collections.singletonMap("message", "Usuario " + userDto.getUsername() + " disabled"));
    }

    @GetMapping("users/enabled/{id}")
    public ResponseEntity<?> enabledUser(@PathVariable Long id) {

        UserResponseDto userDto = userService.enabledUser(id);

        return ResponseEntity.ok().body(Collections.singletonMap("message", "Usuario " + userDto.getUsername() + " enabled"));
    }
}
