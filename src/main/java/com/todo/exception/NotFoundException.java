package com.todo.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String field, String value) {
        super(String.format("Todo Not found with field=[%s], value=[%s]", field, value));
    }
}
