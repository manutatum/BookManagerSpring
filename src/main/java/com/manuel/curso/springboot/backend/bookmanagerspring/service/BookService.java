package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookService {

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByAuthor(String author, Pageable pageable);

    Page<Book> findByTitle(String title, Pageable pageable);

    Page<Book> findByStatus(Status status, Pageable pageable);

    Optional<Book> findById(Long id);

    BookResponseDto save(BookRequestDto book);

    BookResponseDto update(Long id, BookRequestDto dto);

    void deleteAll();

    Optional<Book> deleteById(Long id);

    boolean existsByTitle(String title);
}
