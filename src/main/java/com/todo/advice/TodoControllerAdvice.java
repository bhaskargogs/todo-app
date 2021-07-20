package com.todo.advice;

import com.todo.exception.InvalidConstraintException;
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
        throw new InvalidConstraintException(ex.getMessage());
    }
}
