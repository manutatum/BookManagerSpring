package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Page<Book> findAll(String username, Pageable pageable);

    Page<Book> findByAuthor(String author, String username, Pageable pageable);

    Page<Book> findByTitle(String title, String username, Pageable pageable);

    Page<Book> findByStatus(Status status, String username, Pageable pageable);

    Optional<Book> findById(Long id, String username);

    BookResponseDto save(BookRequestDto book);

    BookResponseDto update(Long id, String username, BookRequestDto dto);

    void deleteAll(String username);

    Optional<Book> deleteById(Long id, String username);

    boolean existsByTitle(String title);
}
