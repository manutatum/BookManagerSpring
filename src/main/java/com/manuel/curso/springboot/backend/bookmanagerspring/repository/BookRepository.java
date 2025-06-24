package com.manuel.curso.springboot.backend.bookmanagerspring.repository;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByUser_Username(String username, Pageable pageable);

    boolean existsBookByTitle(String title);

    Page<Book> findByAuthorContainingIgnoreCaseAndUser_Username(String author, String username, Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCaseAndUser_Username(String title, String username, Pageable pageable);

    Page<Book> findByStatusContainingIgnoreCaseAndUser_Username(Status status, String username, Pageable pageable);

    Optional<Book> findByIdAndUser_Username(Long id, String username);

    String user(User user);

    void deleteAllByUser_Username(String username);
}
