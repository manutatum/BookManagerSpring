package com.manuel.curso.springboot.backend.bookmanagerspring.service;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {

        Optional<Book> updatedBook = bookRepository.findById(id);

        if (updatedBook.isPresent()) {

            Book bookDb =  updatedBook.orElseThrow();

            bookDb.setTitle(book.getTitle());
            bookDb.setAuthor(book.getAuthor());
            bookDb.setStatus(book.isStatus());
            bookDb.setPublishDate(book.getPublishDate());

            return Optional.of(bookRepository.save(bookDb));
        }

        return updatedBook;
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
