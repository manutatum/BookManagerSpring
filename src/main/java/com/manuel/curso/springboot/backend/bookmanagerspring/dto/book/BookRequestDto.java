package com.manuel.curso.springboot.backend.bookmanagerspring.dto.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BookRequestDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private String title;

    @NotBlank
    @Size(min = 5, max = 100)
    private String author;

    @NotNull
    @PastOrPresent(message = "La fechas de publicación debe ser pasada o actual")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate publishDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public BookRequestDto() {
    }

    public BookRequestDto(Book book) {
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publishDate = book.getPublishDate();
        this.status = book.getStatus();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
