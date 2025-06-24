package com.manuel.curso.springboot.backend.bookmanagerspring.dto.user;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Role;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserResponseDto {

    private Long id;

    private String username;

    private List<Role> roles;

    private boolean enabled;

    private List<BookResponseDto> books;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.enabled = user.isEnabled();
        this.books = user.getBooks() != null
                ? user.getBooks()
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList())
                : Collections.emptyList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<BookResponseDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookResponseDto> bookResponseDtos) {
        this.books = bookResponseDtos;
    }
}
