package com.manuel.curso.springboot.backend.bookmanagerspring.repository;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsBookByTitle(String title);

}
