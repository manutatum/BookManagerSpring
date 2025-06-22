package com.manuel.curso.springboot.backend.bookmanagerspring.controller;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> listBooks() {

        return bookService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewBook(@PathVariable Long id) {

        Optional<Book> optionalBook =  bookService.findById(id);

        if (optionalBook.isPresent()) {
            return ResponseEntity.ok().body(optionalBook.orElseThrow());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Book not found"));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody Book book, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        if (bookService.existsByTitle(book.getTitle())) return ResponseEntity.badRequest()
                .body( Collections.singletonMap("message", "This book already exists") );

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));

    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, BindingResult result, @PathVariable Long id) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        Optional<Book> optionalBook =  bookService.update(id, book);

        if (optionalBook.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Book not found"));

        return ResponseEntity.status(HttpStatus.CREATED).body(optionalBook.orElseThrow());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllBooks() {

        bookService.deleteAll();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Books deleted"));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        Optional<Book> optionalBook = bookService.deleteById(id);

        if (optionalBook.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "Book not found"));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Book " + optionalBook.orElseThrow().getTitle() + " deleted"));
    }

    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = result.getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage()
                ));

        return ResponseEntity.badRequest().body(errors);
    }
}