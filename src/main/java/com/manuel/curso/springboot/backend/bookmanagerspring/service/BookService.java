package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Book save(Book book);

    Optional<Book> update(Long id, Book book);

    void deleteAll();

    Optional<Book> deleteById(Long id);

    boolean existsByTitle(String title);
}
