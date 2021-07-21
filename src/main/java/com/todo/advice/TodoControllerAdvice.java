package com.todo.advice;

import com.todo.exception.InvalidConstraintException;
import com.todo.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TodoControllerAdvice {

    @ExceptionHandler(InvalidConstraintException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Todo Constraints...")
    public ResponseEntity<?> handleInvalidConstraintException(InvalidConstraintException ex) {
        return makeResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Todo Not found...")
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {
        return makeResponseEntity(ex, HttpStatus.NOT_FOUND);
    }

    private static ResponseEntity<?> makeResponseEntity(RuntimeException ex, HttpStatus status) {
        return ResponseEntity.status(status).body(ex.getMessage());
    }
}
