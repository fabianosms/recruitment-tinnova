package com.machado.fabiano.recruitmenttinnova.exceptions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class NaoEncontradoHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> notFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome de marca inválido ou ID não encontrado");
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> notFound(EmptyResultDataAccessException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não encontrado");
    }
}