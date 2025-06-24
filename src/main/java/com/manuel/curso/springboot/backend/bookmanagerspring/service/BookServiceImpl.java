package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.BookRepository;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<Book> findAll(String username, Pageable pageable) {
        return bookRepository.findAllByUser_Username(username, pageable);
    }

    @Override
    public Page<Book> findByAuthor(String author, String username, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCaseAndUser_Username(author, username, pageable);
    }

    @Override
    public Page<Book> findByTitle(String title, String username, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCaseAndUser_Username(title, username, pageable);
    }

    @Override
    public Page<Book> findByStatus(Status status, String username, Pageable pageable) {
        return bookRepository.findByStatusContainingIgnoreCaseAndUser_Username(status, username, pageable);
    }

    @Override
    public Optional<Book> findById(Long id, String username) {
        return bookRepository.findByIdAndUser_Username(id, username);
    }

    @Override
    public BookResponseDto save(BookRequestDto dto) {

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setStatus(dto.getStatus());
        book.setPublishDate(dto.getPublishDate());

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new NoSuchElementException("User not found"));

        book.setUser(user);

        Book bookDb = bookRepository.save(book);

        BookResponseDto bookResponseDto = new BookResponseDto(bookDb);

        bookResponseDto.setUserId(bookDb.getUser().getId());

        return bookResponseDto;
    }

    @Override
    public BookResponseDto update(Long id, String username, BookRequestDto dto) {

        //? Buscamos el libro
        Book bookDb = bookRepository.findByIdAndUser_Username(id, username).orElseThrow(() -> new NoSuchElementException("Book not found"));

        //? Cambiamos los datos
        bookDb.setTitle(dto.getTitle());
        bookDb.setAuthor(dto.getAuthor());
        bookDb.setStatus(dto.getStatus());
        bookDb.setPublishDate(dto.getPublishDate());

        Book bookUpdated = bookRepository.save(bookDb);

        BookResponseDto response = new BookResponseDto(bookUpdated);

        response.setUserId(bookDb.getUser().getId());

        return response;
    }

    @Override
    public void deleteAll(String username) {
        bookRepository.deleteAllByUser_Username(username);
    }

    @Override
    public Optional<Book> deleteById(Long id, String username) {

        Optional<Book> deletedBook = bookRepository.findByIdAndUser_Username(id,username);

        deletedBook.ifPresent( book -> bookRepository.delete(book));

        return deletedBook;
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsBookByTitle(title);
    }
}
