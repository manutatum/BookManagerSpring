package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> findByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
    }

    @Override
    public Page<Book> findByTitle(String title, Pageable pageable) {
        return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<Book> findByStatus(Status status, Pageable pageable) {
        return bookRepository.findByStatusContainingIgnoreCase(status, pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookResponseDto save(BookRequestDto dto) {

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setStatus(dto.getStatus());
        book.setPublishDate(dto.getPublishDate());

        Book bookDb = bookRepository.save(book);

        return new BookResponseDto(bookDb);
    }

    @Override
    public BookResponseDto update(Long id, BookRequestDto dto) {

        Optional<Book> updatedBook = bookRepository.findById(id);

        Book bookDb =  updatedBook.orElseThrow(() -> new NoSuchElementException("Book not found"));

        bookDb.setTitle(dto.getTitle());
        bookDb.setAuthor(dto.getAuthor());
        bookDb.setStatus(dto.getStatus());
        bookDb.setPublishDate(dto.getPublishDate());

        return new BookResponseDto(bookDb);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public Optional<Book> deleteById(Long id) {

        Optional<Book> deletedBook = bookRepository.findById(id);

        deletedBook.ifPresent( book -> bookRepository.delete(book));

        return deletedBook;
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsBookByTitle(title);
    }
}
