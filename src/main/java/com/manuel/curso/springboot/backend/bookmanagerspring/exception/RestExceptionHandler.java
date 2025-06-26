package com.manuel.curso.springboot.backend.bookmanagerspring.exception;

import com.manuel.curso.springboot.backend.bookmanagerspring.model.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleJsonParseError(HttpMessageNotReadableException ex) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("message", "JSON mal formado o datos incorrectos");
        errors.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                FieldError::getField,
                error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage()
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFound(NoSuchElementException ex) {

        Map<String, Object> errors = new HashMap<>();

        errors.put("message", ex.getMessage());
        errors.put("status", HttpStatus.NOT_FOUND.value());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleRegisterValidation(IllegalArgumentException ex) {
        Map<String, Object> errors = new HashMap<>();

        if (ex.getMessage() != null && ex.getMessage().startsWith("No enum constant")) {

            errors.put("message", "El estado proporcionado es inválido. Usa uno de los valores permitidos: " + Arrays.toString(Status.values()));

        } else {
            errors.put("message", ex.getMessage());
        }

        errors.put("status", HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
