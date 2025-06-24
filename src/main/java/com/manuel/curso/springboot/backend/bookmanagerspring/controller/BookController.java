package com.manuel.curso.springboot.backend.bookmanagerspring.controller;

import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookRequestDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.book.BookResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.PageResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.dto.user.UserResponseDto;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.Book;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.User;
import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.BookService;
import com.manuel.curso.springboot.backend.bookmanagerspring.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/books")
@Tag(name = "Books", description = "Operaciones para gestionar los libros")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Listar libros",
            description = "Obtiene una lista paginada de libros, con la posibilidad de filtrar por autor ó titulo ó estado"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<PageResponseDto<Book>> listBooks(
            @RequestParam Optional<String> title,
            @RequestParam Optional<Status> status,
            @RequestParam Optional<String> author,
            @Parameter(hidden = true) Pageable pageable) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Page<Book> page;

        if (title.isPresent()){
            page = bookService.findByTitle(title.get(), username, pageable);
        } else if (status.isPresent()){
            page = bookService.findByStatus(status.get(), username, pageable);
        }else if (author.isPresent()){
            page = bookService.findByAuthor(author.get(), username, pageable);
        } else {
            page = bookService.findAll(username, pageable);
        }

        return ResponseEntity.ok().body(new PageResponseDto<>(page));

    }

    @Operation(
            summary = "Mostrar un libro",
            description = "Obtiene un libro"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Libro obtenido correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no existente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> viewBook(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Book> optionalBook =  bookService.findById(id, username);

        BookResponseDto bookResponseDto = new BookResponseDto(optionalBook.orElseThrow(() -> new NoSuchElementException("Book not found")));

        return ResponseEntity.ok().body(bookResponseDto);
    }

    @Operation(
            summary = "Crear un nuevo libro",
            description = "Crea un nuevo libro con sus respectivos campos"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "201", description = "Libro creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> createBook(@Valid @RequestBody BookRequestDto dto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserResponseDto userDto = userService.findByUsername(username);

        dto.setUserId(userDto.getId());

        if (bookService.existsByTitle(dto.getTitle())) return ResponseEntity.badRequest()
                .body( Collections.singletonMap("message", "This book already exists") );

        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(dto));

    }

    @Operation(
            summary = "Editar un libro",
            description = "Edita un libro ya existente"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "200", description = "Libro modificado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Libro no existente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody BookRequestDto dto, @PathVariable Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        BookResponseDto dtoResponse =  bookService.update(id, username, dto);

        return ResponseEntity.ok().body(dtoResponse);
    }

    @Operation(
            summary = "Eliminar todos los libros",
            description = "Elimina todos los libros que existan en la base de datos"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "202", description = "Libros eliminados correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping
    public ResponseEntity<?> deleteAllBooks() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        bookService.deleteAll(username);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Books deleted"));
    }

    @Operation(
            summary = "Elimina un libro",
            description = "Elimina un libro en concreto"
    )
    @ApiResponses( value =  {
            @ApiResponse(responseCode = "202", description = "Libro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no existente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Book> optionalBook = bookService.deleteById(id, username);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("message", "Book " + optionalBook.orElseThrow(() -> new NoSuchElementException("Book not found")).getTitle() + " deleted"));
    }
}